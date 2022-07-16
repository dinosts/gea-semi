package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.entities.SemiGetResponseBody;
import gr.tsitoumis.geasemi.utils.Paging;
import gr.tsitoumis.geasemi.semi.repositories.OpportunitiesRepository;
import gr.tsitoumis.geasemi.utils.PagingResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemiService {

    @Autowired
    private OpportunitiesRepository repository;

    public SemiGetResponseBody getProjectOpportunities(String projectName, Paging paging) {
        Pageable pageable = PageRequest.of(paging.getPageNumber(), paging.getPageSize());
        List opportunities = repository.findByProjectName(projectName, pageable);

        int allItems = repository.findByProjectNameCount(projectName);

        PagingResponseBody pagingResponseBody = new PagingResponseBody(paging.getPageNumber(), paging.getPageSize(), allItems);

        SemiGetResponseBody result = new SemiGetResponseBody(opportunities, pagingResponseBody);

        return result;
    }
}
