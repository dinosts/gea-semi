package gr.tsitoumis.geasemi.utils;

import org.springframework.http.HttpStatus;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class Commands {

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

        int exitCode = runCmd("git clone " + project, "./git-repositories");

        if (exitCode == 128) {
            throw new GeaSemiException("Project already cloned", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (exitCode != 0) {
            throw new GeaSemiException("Git clone FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Semi
    public static void semiRun(String name, String lang, String version) throws Exception {

        Path projectPath = Paths.get("git-repositories/" + name).toAbsolutePath();
        Path semiPath = Paths.get("jars/semi-0.0.1-jar-with-dependencies.jar").toAbsolutePath();
        Path semiExtractPosition = Paths.get("git-repositories/" + name).toAbsolutePath();

        String command = "java -jar" + " " + semiPath + " " + lang + " " + name + " " + version + " " + projectPath + " " + dbString;

        int exitCode = runCmd(command, semiExtractPosition.toString());

        if (exitCode != 0) {
            throw new GeaSemiException("Semi exit code 0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Gea
    public static void geaRun(String name, String lang) throws Exception {
        Path projectPath = Paths.get("git-repositories/" + name).toAbsolutePath();
        Path geaPath = Paths.get("jars/DeRec-GEA-1.0-SNAPSHOT-jar-with-dependencies.jar").toAbsolutePath();
        Path geaExtractPosition = Paths.get("git-repositories/" + name).toAbsolutePath();

        String command = "java -jar" + " " + geaPath + " " + lang + " " + name + " " + projectPath + " " + dbString;

        int exitCode = runCmd(command, geaExtractPosition.toString());

        if (exitCode != 0) {
            throw new GeaSemiException("Gea exit code 0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public static void deleteProject(String projectName) throws Exception {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        if (isWindows) {
            runCmd("rd /s /q " + projectName, "./git-repositories");
        } else {
            runCmd("rm -rf " + projectName, "./git-repositories");
        }
    }


}
