package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.entities.Opportunities;
import gr.tsitoumis.geasemi.semi.entities.SemiOpportunitiesResponseBody;
import gr.tsitoumis.geasemi.utils.Pagination;
import gr.tsitoumis.geasemi.utils.PagingResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SemiService {

    @Autowired
    private SemiRepository repository;

    public SemiOpportunitiesResponseBody getProjectOpportunities(String projectName, int page, int pageSize) throws Exception {
        Pagination pagination = new Pagination(page, pageSize);
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());
        Page<Opportunities> opportunities = repository.findByProjectName(projectName, pageable);

        int allPages = opportunities.getTotalPages();

        PagingResponseBody pagingResponseBody = new PagingResponseBody(pagination.getPageNumber(), pagination.getPageSize(), allPages);

        SemiOpportunitiesResponseBody result = new SemiOpportunitiesResponseBody(opportunities.getContent(), pagingResponseBody);

        return result;
    }
}
