package gr.tsitoumis.geasemi.semi.tool.analyser;

import java.io.File;

import gr.tsitoumis.geasemi.semi.tool.splitlongmethod.JavaClass;
import gr.tsitoumis.geasemi.semi.tool.splitlongmethod.SplitLongMethod;

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

        long start = System.currentTimeMillis();

        SplitLongMethod splitlongmethod = new SplitLongMethod("./" + file.getName() + "_parsed.txt",
                file.getAbsolutePath());
        clazz = splitlongmethod.parse();
        clazz.setFile(file.getAbsolutePath());
		
        return clazz;
    }
}
