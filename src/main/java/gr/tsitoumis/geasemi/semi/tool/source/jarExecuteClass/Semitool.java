package gr.tsitoumis.geasemi.semi.tool.source.jarExecuteClass;

import java.io.File;
import java.nio.file.Paths;

import gr.tsitoumis.geasemi.semi.SemiService;
import gr.tsitoumis.geasemi.semi.tool.source.utils.ExtraParseUtils;

public class Semitool {

    public void run(SemiService service, String language, String name, String version, String path) throws Exception {

        doEndStuff();

        if (language.equals("c") && language.equals("cpp") && language.equals("java")) {
            System.out.println("Wrong programming language, only accepts: c / cpp / java");

            System.exit(1);
            return;
        }

        long start = System.currentTimeMillis();
        long end;
        BasicController controller = new BasicController(service, language, name, version, path);
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

    private void doEndStuff() {
        File folder = new File(System.getProperty("user.dir"));
        File fileList[] = folder.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().endsWith("_parsed.txt")) {
                fileList[i].delete();
            }
        }
    }

}
