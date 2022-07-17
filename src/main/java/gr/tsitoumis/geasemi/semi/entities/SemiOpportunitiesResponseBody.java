package gr.tsitoumis.geasemi.semi.entities;

import gr.tsitoumis.geasemi.utils.PagingResponseBody;
import org.springframework.data.domain.Page;

import java.util.List;

public class SemiOpportunitiesResponseBody {

    List<Opportunities> opportunities;
    PagingResponseBody pagination;

    public SemiOpportunitiesResponseBody(List<Opportunities> opportunities, PagingResponseBody paging) {
        this.opportunities = opportunities;
        this.pagination = paging;
    }

    public List<Opportunities> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunities> opportunities) {
        this.opportunities = opportunities;
    }

    public PagingResponseBody getPaging() {
        return pagination;
    }

    public void setPaging(PagingResponseBody pagination) {
        this.pagination = pagination;
    }

}
