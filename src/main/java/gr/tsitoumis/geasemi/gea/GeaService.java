package gr.tsitoumis.geasemi.gea;


import gr.tsitoumis.geasemi.gea.entities.GeaClasses;
import gr.tsitoumis.geasemi.gea.entities.GeaClassesResponseBody;
import gr.tsitoumis.geasemi.utils.Pagination;
import gr.tsitoumis.geasemi.utils.PaginationResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        GeaClassesResponseBody result = new GeaClassesResponseBody(geaClasses.getContent(), paginationResponseBody);

        return result;
    }
}
