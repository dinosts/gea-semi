package gr.tsitoumis.geasemi.gea.entities;

import java.util.List;

public class GeaPackagesWithClasses extends GeaPackages {

    public GeaPackagesWithClasses(GeaPackages geaPackage, List<GeaClasses> classes) {
        super(geaPackage.getId(), geaPackage.getName(), geaPackage.getCohesion(), geaPackage.getCoupling(), geaPackage.getProjectName(), geaPackage.getIsNew());
        this.classes = classes;
    }

    public GeaPackagesWithClasses(Long id, String name, double cohesion, double coupling, String projectName, boolean isNew, List<GeaClasses> classes) {
        super(id, name, cohesion, coupling, projectName, isNew);
        this.classes = classes;
    }

    public List<GeaClasses> getClasses() {
        return classes;
    }

    public void setClasses(List<GeaClasses> classes) {
        this.classes = classes;
    }

    List<GeaClasses> classes;
}