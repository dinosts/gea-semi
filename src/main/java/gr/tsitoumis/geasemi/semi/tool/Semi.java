package gr.tsitoumis.geasemi.semi.tool;

import java.io.File;

public class Semi {
    String language;
    String name;
    String projectVersion;
    String path;

    public Semi(String language, String name, String projectVersion, String path) {
        this.language = language;
        this.name = name;
        this.projectVersion = projectVersion;
        this.path = path;
    }

    public static void run(String language, String name, String projectVersion, String path) {

        doEndStuff();

        if (!language.equals("c") && !language.equals("cpp") && !language.equals("java")) {
            System.out.println("Wrong programming language, only accepts: c / cpp / java");

            throw new Error("Wrong programming language");
        }

        long start = System.currentTimeMillis();
        long end;

        BasicController controller = new BasicController(language, name, projectVersion, path, "195.251.210.147:3363", "metrics", "metrics", "308d139ce20d7695a301d723f8bf379d1fc7dd899966d8102578d4acc85bbf8e");
        boolean result = controller.runExperiment();

        doEndStuff();

        if (result) {
            System.out.println("Executed correctly");
            end = System.currentTimeMillis();
            System.out.println("Total Time: " + ((end - start) / 1000) / 60 + " minutes");
            System.exit(0);
        } else {
            System.out.println("There was an error");
            System.exit(1);
        }

    }

    private static void doEndStuff() {
        File folder = new File(System.getProperty("user.dir"));
        File fileList[] = folder.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().endsWith("_parsed.txt")) {
                fileList[i].delete();
            }
        }
    }
}