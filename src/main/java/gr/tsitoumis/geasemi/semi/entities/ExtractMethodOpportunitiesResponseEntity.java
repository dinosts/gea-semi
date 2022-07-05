package gr.tsitoumis.geasemi.semi.entities;

import java.util.List;


public class ExtractMethodOpportunitiesResponseEntity {

    private List<MethodOpportunities> opportunities;

    public ExtractMethodOpportunitiesResponseEntity() {
    }

    public ExtractMethodOpportunitiesResponseEntity(List<MethodOpportunities> opportunities) {
        this.opportunities = opportunities;
    }

    public List<MethodOpportunities> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<MethodOpportunities> opportunities) {
        this.opportunities = opportunities;
    }


}