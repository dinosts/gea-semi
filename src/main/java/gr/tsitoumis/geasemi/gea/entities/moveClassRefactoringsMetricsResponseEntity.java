package gr.tsitoumis.geasemi.gea.entities;


public class moveClassRefactoringsMetricsResponseEntity {

    private moveClassRefactoringsMetrics metrics;

    public moveClassRefactoringsMetricsResponseEntity() {
    }

    public moveClassRefactoringsMetricsResponseEntity(moveClassRefactoringsMetrics metrics) {
        this.metrics = metrics;
    }

    public moveClassRefactoringsMetrics getMoveClassProjectMetrics() {
        return metrics;
    }

    public void setMoveClassRefactoringsMetrics(moveClassRefactoringsMetrics metrics) {
        this.metrics = metrics;
    }

}