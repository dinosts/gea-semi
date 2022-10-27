package gr.tsitoumis.geasemi.gea.entities;

public class GeaProjectsReponse {

    private String name;
    private double coupling_old;
    private double coupling_new;
    private double coupling_difference;

    private double cohesion_old;
    private double cohesion_new;
    private double cohesion_difference;


    public GeaProjectsReponse(String name, double coupling_old, double cohesion_old, double coupling_new, double cohesion_new, double coupling_difference, double cohesion_difference) {
        this.name = name;
        this.coupling_old = coupling_old;
        this.cohesion_old = cohesion_old;
        this.coupling_new = coupling_new;
        this.cohesion_new = cohesion_new;
        this.coupling_difference = coupling_difference;
        this.cohesion_difference = cohesion_difference;
    }

    public GeaProjectsReponse(GeaProjects project, double coupling_difference, double cohesion_difference) {
        this.name = project.getName();
        this.coupling_old = project.getCouplingOld();
        this.cohesion_old = project.getCohesionOld();
        this.coupling_new = project.getCouplingNew();
        this.cohesion_new = project.getCouplingNew();
        this.coupling_difference = coupling_difference;
        this.cohesion_difference = cohesion_difference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoupling_old() {
        return coupling_old;
    }

    public void setCoupling_old(double coupling_old) {
        this.coupling_old = coupling_old;
    }

    public double getCohesion_old() {
        return cohesion_old;
    }

    public void setCohesion_old(double cohesion_old) {
        this.cohesion_old = cohesion_old;
    }

    public double getCoupling_new() {
        return coupling_new;
    }

    public void setCoupling_new(double coupling_new) {
        this.coupling_new = coupling_new;
    }

    public double getCohesion_new() {
        return cohesion_new;
    }

    public void setCohesion_new(double cohesion_new) {
        this.cohesion_new = cohesion_new;
    }

    public double getCoupling_difference() {
        return coupling_difference;
    }

    public void setCoupling_difference(double coupling_difference) {
        this.coupling_difference = coupling_difference;
    }

    public double getCohesion_difference() {
        return cohesion_difference;
    }

    public void setCohesion_difference(double cohesion_difference) {
        this.cohesion_difference = cohesion_difference;
    }


}
