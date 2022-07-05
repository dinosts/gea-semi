package gr.tsitoumis.geasemi.semi.entities;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;

public class MethodOpportunities {

    private String name;
    private double colorValue;
    private double value;

    public MethodOpportunities() {
    }

    public MethodOpportunities(String name, double colorValue, double value) {
        this.name = name;
        this.colorValue = colorValue;
        this.value = value;
    }

    public MethodOpportunities(Opportunities opportunities) {
        this.name = opportunities.getClassName() + ":" + opportunities.getMethodName() + "<br> Line Start: " + opportunities.getLineStart() + "<br> Line End: " + opportunities.getLineEnd();
        this.colorValue = opportunities.getCohesionBenefiit();
        this.value = opportunities.getLinesOfCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getColorValue() {
        return colorValue;
    }

    public void setColorValue(double colorValue) {
        this.colorValue = colorValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}