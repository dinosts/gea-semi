package gr.tsitoumis.geasemi.semi.entities;

import gr.tsitoumis.geasemi.utils.PaginationResponseBody;

import java.util.List;

public class SemiOpportunitiesResponseBody {

    List<Opportunities> opportunities;
    PaginationResponseBody pagination;

    public SemiOpportunitiesResponseBody(List<Opportunities> opportunities, PaginationResponseBody paging) {
        this.opportunities = opportunities;
        this.pagination = paging;
    }

    public List<Opportunities> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunities> opportunities) {
        this.opportunities = opportunities;
    }

    public PaginationResponseBody getPaging() {
        return pagination;
    }

    public void setPaging(PaginationResponseBody pagination) {
        this.pagination = pagination;
    }

}
