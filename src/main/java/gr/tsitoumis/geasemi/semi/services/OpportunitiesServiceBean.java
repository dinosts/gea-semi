package gr.tsitoumis.geasemi.semi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.tsitoumis.geasemi.semi.entities.MethodOpportunities;
import gr.tsitoumis.geasemi.semi.repositories.OpportunitiesRepository;

@Service
public class OpportunitiesServiceBean implements OpportunitiesService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OpportunitiesRepository opportunitiesRepository;

    @Override
    public List<MethodOpportunities> extractMethodOpportunitiesByProjectName(String projectName) {
        logger.info("> extractMethodOpportunitiesByProjectName: {}", projectName);

        List<MethodOpportunities> methodOpportunities = opportunitiesRepository.findByProjectName(projectName).stream().map(MethodOpportunities::new).collect(Collectors.toList());

        logger.info("< extractMethodOpportunitiesByProjectName: {}", projectName);
        return methodOpportunities;
    }

}