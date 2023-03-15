package gr.tsitoumis.geasemi.semi.tool.source.jarExecuteClass;

import java.io.File;
import java.nio.file.Paths;

import gr.tsitoumis.geasemi.semi.tool.source.utils.ExtraParseUtils;

public class Semitool {

    public static void run(String language, String name, String version, String path) throws Exception {

        doEndStuff();

        if (language.equals("c") && language.equals("cpp") && language.equals("java")) {
            System.out.println("Wrong programming language, only accepts: c / cpp / java");

            System.exit(1);
            return;
        }

        long start = System.currentTimeMillis();
        long end;
        BasicController controller = new BasicController(language, name, version, path, "195.251.210.147:3363", "metrics", "metrics", "308d139ce20d7695a301d723f8bf379d1fc7dd899966d8102578d4acc85bbf8e");
        boolean result = controller.runExperiment();

        doEndStuff();

        if (result) {
            System.out.println("Executed correctly");
            end = System.currentTimeMillis();
            System.out.println("Total Time: " + ((end - start) / 1000) / 60 + " minutes");
        } else {
            throw new Exception("An error has accrued");
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
