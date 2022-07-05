package gr.tsitoumis.geasemi.gea.entities;

import java.util.List;

public class MoveClassRefactoringsNameChildren {

    private String name;
    private List<MoveClassRefactoringsChild> children;

    public MoveClassRefactoringsNameChildren() {
    }

    public MoveClassRefactoringsNameChildren(String name, List<MoveClassRefactoringsChild> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MoveClassRefactoringsChild> getChildren() {
        return children;
    }

    public void setChildren(List<MoveClassRefactoringsChild> children) {
        this.children = children;
    }

}