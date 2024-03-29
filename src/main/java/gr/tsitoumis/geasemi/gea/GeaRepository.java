package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.entities.GeaClasses;
import gr.tsitoumis.geasemi.gea.entities.GeaPackages;
import gr.tsitoumis.geasemi.gea.entities.GeaProjects;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GeaRepository extends PagingAndSortingRepository<GeaClasses, Integer> {

    @Query("SELECT t FROM GeaClasses t WHERE t.projectName = ?1")
    Page<GeaClasses> findGeaClassesByProjectName(String projectName, Pageable pageable);

    @Query("SELECT t FROM GeaPackages t WHERE t.projectName = ?1")
    Page<GeaPackages> findGeaPackagesByProjectName(String projectName, Pageable pageable);

    @Query("SELECT t FROM GeaClasses t WHERE t.packageID = ?1 AND t.isNew = ?2 ")
    List<GeaClasses> findGeaClassesByPackageId(int packageId, boolean isNew);

    @Query("SELECT t FROM GeaProjects t WHERE t.name = ?1")
    GeaProjects findGeaProject(String projectName);
}
