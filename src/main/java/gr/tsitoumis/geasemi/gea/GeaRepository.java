package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GeaRepository extends PagingAndSortingRepository<Opportunities, Integer> {

    @Query("SELECT t FROM GeaClasses t WHERE t.name = ?1")
    List findGeaClassesByProjectName(String projectName, Pageable pageable);

}
