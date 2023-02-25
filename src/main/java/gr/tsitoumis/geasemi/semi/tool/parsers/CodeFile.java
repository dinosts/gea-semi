package gr.tsitoumis.geasemi.semi.tool.parsers;


import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Nikos
 */
public abstract class CodeFile implements Serializable {
    public File file;
    public int fanOut;
    public HashMap<String, Integer> methodsLOC;
    public HashMap<String, Integer> methodsCC;
    public int cohesion;

    public CodeFile(File file) {
        this.file = file;
        methodsLOC = new HashMap<>();
        methodsCC = new HashMap<>();
    }

    public abstract void parse();

    public abstract void calculateCohesion();

}
