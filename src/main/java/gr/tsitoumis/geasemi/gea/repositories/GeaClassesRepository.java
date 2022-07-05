package gr.tsitoumis.geasemi.gea.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gr.tsitoumis.geasemi.gea.entities.*;


@Repository
public interface GeaClassesRepository extends JpaRepository<GeaClasses, Long> {

    @Query(value = "SELECT new gr.tsitoumis.geasemi.gea.entities.ClassPackageProjectName(gc.name, gp.name, gc.projectName) FROM GeaClasses gc LEFT JOIN GeaPackages gp ON gc.packageID=gp.id WHERE gc.isNew = :isNew AND gc.projectName = :projectName ORDER BY gp.name ASC")
    List<ClassPackageProjectName> getMoveClassRefactoringsByProjectNameAndIsNew(String projectName, boolean isNew);


    @Query(value = "SELECT new gr.tsitoumis.geasemi.gea.entities.moveClassRefactoringsMetrics(m.name, m.couplingOld, m.couplingNew, m.cohesionOld, m.cohesionNew) FROM GeaProjects m WHERE m.name = :projectID")
    moveClassRefactoringsMetrics getMoveClassRefactoringsMetrics(String projectID);

}