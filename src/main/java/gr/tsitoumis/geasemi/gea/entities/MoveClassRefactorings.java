package gr.tsitoumis.geasemi.gea.entities;

import java.util.Collection;

public class MoveClassRefactorings {

    private String name;
    private boolean toggled = true;
    private Collection<MoveClassRefactoringsNameChildren> children;

    public MoveClassRefactorings() {
    }

    public MoveClassRefactorings(String name) {
        this.name = name;
    }

    public MoveClassRefactorings(String name, Collection<MoveClassRefactoringsNameChildren> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public Collection<MoveClassRefactoringsNameChildren> getChildren() {
        return children;
    }

    public void setChildren(Collection<MoveClassRefactoringsNameChildren> children) {
        this.children = children;
    }


}