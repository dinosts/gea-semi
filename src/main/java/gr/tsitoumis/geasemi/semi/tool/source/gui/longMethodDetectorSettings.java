/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.tsitoumis.geasemi.semi.tool.source.gui;

/**
 * @author angor
 **/

public class longMethodDetectorSettings {
    // private final LongMethodDetector parentFrame;

    private String cohesion_metric = "lcom2";

    public String getRankingMetric() {
        return this.cohesion_metric;
    }

    /**
     * Creates new form longMethodDetectorSettings
     *
     * @param parentFrame
     */
    public longMethodDetectorSettings(LongMethodDetector parentFrame) {
        // ***UI initComponents();
        setDefaultValues();
        updateComponents();

        // this.parentFrame = parentFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void setDefaultValues() {
        this.cohesion_metric = "lcom2";
    }

    private void updateComponents() {
        // this.rankingMetricComboBox.setSelectedItem(this.cohesion_metric);
    }
}