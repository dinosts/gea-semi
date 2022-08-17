package gr.tsitoumis.geasemi.gea.entities;

import gr.tsitoumis.geasemi.utils.PaginationResponseBody;

import java.util.List;

public class GeaClassesResponseBody {

    List<GeaClasses> geaClasses;
    PaginationResponseBody pagination;

    public GeaClassesResponseBody(List<GeaClasses> geaClasses, PaginationResponseBody paging) {
        this.geaClasses = geaClasses;
        this.pagination = paging;
    }

    public List<GeaClasses> getGeaClasses() {
        return geaClasses;
    }

    public void setGeaClasses(List<GeaClasses> geaClasses) {
        this.geaClasses = geaClasses;
    }

    public PaginationResponseBody getPaging() {
        return pagination;
    }

    public void setPaging(PaginationResponseBody pagination) {
        this.pagination = pagination;
    }

}
