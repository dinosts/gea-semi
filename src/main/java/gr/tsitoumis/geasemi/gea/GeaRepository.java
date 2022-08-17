package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.entities.GeaClasses;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;


public interface GeaRepository extends PagingAndSortingRepository<GeaClasses, Integer> {

    @Query("SELECT t FROM GeaClasses t WHERE t.projectName = ?1")
    Page<GeaClasses> findGeaClassesByProjectName(String projectName, Pageable pageable);

}
