package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;
import gr.tsitoumis.geasemi.semi.entities.SemiOpportunitiesResponseBody;
import gr.tsitoumis.geasemi.utils.Pagination;
import gr.tsitoumis.geasemi.utils.PaginationResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.StreamSupport;


@Service
public class SemiService {

    @Autowired
    private SemiRepository repository;

    public SemiOpportunitiesResponseBody getProjectOpportunities(String projectName, int page, int pageSize) throws Exception {
        Pagination pagination = new Pagination(page, pageSize);
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());
        Page<Opportunities> opportunities = repository.findByProjectName(projectName, pageable);

        int allPages = opportunities.getTotalPages();

        PaginationResponseBody paginationResponseBody = new PaginationResponseBody(pagination.getPageNumber(), pagination.getPageSize(), allPages);

        SemiOpportunitiesResponseBody result = new SemiOpportunitiesResponseBody(opportunities.getContent(), paginationResponseBody);

        return result;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean saveOpportunitiesArray(ArrayList<Opportunities> array) {

        Iterable result = repository.saveAll(array);

        if (result instanceof ArrayList ){
            return !((ArrayList) result).isEmpty();
        }

        return false;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public long deleteByProjectNameAndProjectVersion(String projectName, int projectVersion) {
        return repository.deleteByProjectNameAndProjectVersion(projectName,projectVersion);
    }


}
