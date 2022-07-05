package gr.tsitoumis.geasemi.semi.repositories;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunitiesRepository extends JpaRepository<Opportunities, Integer> {

    @Query(value = "SELECT o FROM Opportunities o WHERE o.projectName = :projectName")
    List<Opportunities> findByProjectName(String projectName);

}