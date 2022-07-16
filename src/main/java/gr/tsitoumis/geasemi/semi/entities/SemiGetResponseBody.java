package gr.tsitoumis.geasemi.semi.entities;

import gr.tsitoumis.geasemi.utils.PagingResponseBody;

import java.util.List;

public class SemiGetResponseBody {

    List opportunities;
    PagingResponseBody paging;

    public SemiGetResponseBody(List opportunities, PagingResponseBody paging) {
        this.opportunities = opportunities;
        this.paging = paging;
    }

    public List getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List opportunities) {
        this.opportunities = opportunities;
    }

    public PagingResponseBody getPaging() {
        return paging;
    }

    public void setPaging(PagingResponseBody paging) {
        this.paging = paging;
    }

}
