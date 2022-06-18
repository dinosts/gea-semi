package gr.tsitoumis.geasemi.services;

import gr.tsitoumis.geasemi.semi.schemas.SemiRunRequestBody;

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

    private static void runCmd(String cmd) throws Exception {
        runCmd(cmd, "./gitRepositories");
    }

    private static void runCmd(String cmd, String directory) throws Exception {

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
        if (exitCode != 0) {
            throw new Exception("Exit code not 0");
        }
    }

    // Git

    public static void gitClone(String project) throws Exception {
        try {
            runCmd("git clone " + project);
        } catch (Exception e) {
            throw new Exception("Git clone failed.");
        }
    }

    // Semi
    //TODO: PLACE SEMI IN PROJ DIR
    public static void semiRun(SemiRunRequestBody request) throws Exception {
        try {
            String name = GitTools.getProjectName(request.getUrl());
            String lang = request.getLanguage();
            String version = request.getVersion();

            Path path = Paths.get("gitRepositories/" + name).toAbsolutePath();

            String command = "java -jar semi-0.0.1-jar-with-dependencies.jar" + " " + lang + " " + name + " " + version + " " + path + " " + dbString;

            runCmd(command);
        } catch (Exception e) {
            throw new Exception("semi analysis failed");
        }
    }
}
