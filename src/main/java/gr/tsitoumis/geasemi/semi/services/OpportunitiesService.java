package gr.tsitoumis.geasemi.semi.services;

import java.util.List;

import gr.tsitoumis.geasemi.semi.entities.MethodOpportunities;

public interface OpportunitiesService {

    List<MethodOpportunities> extractMethodOpportunitiesByProjectName(String projectName);

}