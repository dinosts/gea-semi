package gr.tsitoumis.geasemi.services;

import gr.tsitoumis.geasemi.semi.schemas.SemiRunRequestBody;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class CommandService {

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    // TODO: CONFIG SECRETS
    private static String dbString = "195.251.210.147:3363 metrics metrics 308d139ce20d7695a301d723f8bf379d1fc7dd899966d8102578d4acc85bbf8e";

    private static int runCmd(String cmd, String directory) throws Exception {

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        ProcessBuilder builder = new ProcessBuilder();

        if (isWindows) {
            builder.command("cmd.exe", "/c", cmd);
        } else {
            builder.command("sh", "-c", cmd);
        }

        builder.directory(new File(directory));
        Process process = builder.start();
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);


        int exitCode = process.waitFor();

        return exitCode;
    }

    // Git

    public static void gitClone(String project) throws Exception {

        int exitCode = runCmd("git clone " + project, "./gitRepositories");

        if (exitCode == 128) {
            throw new GeaSemiException("Project already cloned");
        }

        if (exitCode != 0) {
            throw new GeaSemiException("Git clone FAILED");
        }
    }

    // Semi
    public static void semiRun(String name, String lang, String version) throws Exception {
        try {

            Path projectPath = Paths.get("gitRepositories/" + name).toAbsolutePath();
            Path semiPath = Paths.get("geasemiJars/semi-0.0.1-jar-with-dependencies.jar").toAbsolutePath();
            Path semiExtractPosition = Paths.get("gitRepositories/" + name).toAbsolutePath();

            String command = "java -jar" + " " + semiPath + " " + lang + " " + name + " " + version + " " + projectPath + " " + dbString;

            int exitCode = runCmd(command, semiExtractPosition.toString());

            if (exitCode != 0) {
                throw new GeaSemiException("Semi analysis FAILED.");
            }

        } catch (Exception e) {
            throw new Exception("semi analysis failed");
        }
    }


    public static void deleteProject(String projectName) throws Exception {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        if (isWindows) {
            runCmd("rd /s /q " + projectName, "./gitRepositories");
        } else {
            runCmd("rm -rf " + projectName, "./gitRepositories");
        }
    }


}
