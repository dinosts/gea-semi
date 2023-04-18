package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SemiRepository extends PagingAndSortingRepository<Opportunities, Integer> {
    @Query("SELECT t FROM Opportunities t WHERE t.projectName = ?1")
    Page<Opportunities> findByProjectName(String projectName, Pageable pageable);

    @Query("SELECT COUNT(t) FROM Opportunities t WHERE t.projectName = ?1")
    int findByProjectNameCount(String projectName);

    long deleteByProjectNameAndProjectVersion(String projectName, int projectVersion);
}
