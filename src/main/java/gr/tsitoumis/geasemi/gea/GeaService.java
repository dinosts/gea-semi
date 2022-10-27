package gr.tsitoumis.geasemi.gea;


import gr.tsitoumis.geasemi.gea.entities.*;
import gr.tsitoumis.geasemi.utils.Pagination;
import gr.tsitoumis.geasemi.utils.PaginationResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeaService {
    @Autowired
    private GeaRepository repository;

    public GeaClassesResponseBody getGeaClasses(String projectName, int page, int pageSize) throws Exception {
        Pagination pagination = new Pagination(page, pageSize);
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());
        Page<GeaClasses> geaClasses = repository.findGeaClassesByProjectName(projectName, pageable);

        int allPages = geaClasses.getTotalPages();

        PaginationResponseBody paginationResponseBody = new PaginationResponseBody(pagination.getPageNumber(), pagination.getPageSize(), allPages);

        return new GeaClassesResponseBody(geaClasses.getContent(), paginationResponseBody);
    }

    public GeaPackagesResponse getGeaPackagesWithMovableClasses(String projectName, int page, int pageSize) throws Exception {
        Pagination pagination = new Pagination(page, pageSize);
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());
        Page<GeaPackages> geaPackages = repository.findGeaPackagesByProjectName(projectName, pageable);

        int allPages = geaPackages.getTotalPages();

        List<GeaPackagesWithClasses> packages = new ArrayList<GeaPackagesWithClasses>();


        for (GeaPackages geaPackage : geaPackages.getContent()) {
            // isNew = true means movable.
            List<GeaClasses> geaClasses = repository.findGeaClassesByPackageId(geaPackage.getId().intValue(), true);
            if (!geaClasses.isEmpty()) {
                packages.add(new GeaPackagesWithClasses(geaPackage, geaClasses));
            }
        }


        PaginationResponseBody paginationResponseBody = new PaginationResponseBody(pagination.getPageNumber(), pagination.getPageSize(), allPages);

        return new GeaPackagesResponse(packages, paginationResponseBody);
    }

    public GeaProjectsReponse getGeaProject(String projectName) {
        GeaProjects project = repository.findGeaProject(projectName);

        double cohesion_difference = project.getCohesionOld() - project.getCohesionNew();
        double coupling_difference = project.getCouplingOld() - project.getCouplingNew();

        return new GeaProjectsReponse(project, Math.abs(coupling_difference), Math.abs(cohesion_difference));
    }

}
