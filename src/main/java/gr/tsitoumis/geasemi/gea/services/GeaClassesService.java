package gr.tsitoumis.geasemi.gea.services;


import java.util.List;

import gr.tsitoumis.geasemi.gea.entities.ClassPackageProjectName;
import gr.tsitoumis.geasemi.gea.entities.moveClassRefactoringsMetrics;


public interface GeaClassesService {

    List<ClassPackageProjectName> getMoveClassRefactoringsByProjectNameAndIsNew(String projectName, boolean isNew);

    moveClassRefactoringsMetrics getMoveClassRefactoringsMetrics(String projectName);

}