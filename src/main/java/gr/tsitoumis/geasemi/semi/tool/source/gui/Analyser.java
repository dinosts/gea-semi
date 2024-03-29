package gr.tsitoumis.geasemi.semi.tool.source.gui;

import java.io.File;

import gr.tsitoumis.geasemi.semi.tool.source.splitlongmethod.JavaClass;
import gr.tsitoumis.geasemi.semi.tool.source.splitlongmethod.SplitLongMethod;
import gr.tsitoumis.geasemi.semi.tool.source.utils.ExtraParseUtils;
import gr.tsitoumis.geasemi.semi.tool.source.utils.Utilities;

/**
 * @author Antonis Gkortzis (s2583070, antonis.gkortzis@gmail.com)
 */
public class Analyser {

    private static File file;

    public static File getFile() {
        return file;
    }

    public void setFile(File file) {
        Analyser.file = file;
    }

    public JavaClass performAnalysis() {
        JavaClass clazz = null;

        if (file.isDirectory()) {
//            System.out.println("You selected the folder: " + file.getAbsolutePath());
        } else {
            long start = System.currentTimeMillis();


            SplitLongMethod splitlongmethod = new SplitLongMethod("./" + file.getName() + "_parsed.txt",
                    file.getAbsolutePath());
            clazz = splitlongmethod.parse();
            clazz.setFile(file.getAbsolutePath());
            Utilities.writeCSV("./clusters.txt");
            if (LongMethodDetector.DebugMode) {
                System.out.println("\nLong mathod detection completed in "
                        + Utilities.getDuration(start, System.currentTimeMillis()));
            }
        }
        return clazz;
    }
}
