package gr.tsitoumis.geasemi.gea.entities;


public class MoveClassRefactoringsResponseEntity {

    private MoveClassRefactorings moveClassRefactorings;

    public MoveClassRefactoringsResponseEntity() {
    }

    public MoveClassRefactoringsResponseEntity(MoveClassRefactorings moveClassRefactorings) {
        this.moveClassRefactorings = moveClassRefactorings;
    }

    public MoveClassRefactorings getMoveClassRefactorings() {
        return moveClassRefactorings;
    }

    public void setMoveClassRefactorings(MoveClassRefactorings moveClassRefactorings) {
        this.moveClassRefactorings = moveClassRefactorings;
    }


}