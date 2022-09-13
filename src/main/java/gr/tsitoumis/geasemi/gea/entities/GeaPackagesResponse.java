package gr.tsitoumis.geasemi.gea.entities;


import gr.tsitoumis.geasemi.utils.PaginationResponseBody;

import java.util.List;

public class GeaPackagesResponse {
    List<GeaPackagesWithClasses> geaPackages;
    PaginationResponseBody pagination;

    public List<GeaPackagesWithClasses> getGeaPackages() {
        return geaPackages;
    }

    public void setGeaPackages(List<GeaPackagesWithClasses> geaPackages) {
        this.geaPackages = geaPackages;
    }

    public PaginationResponseBody getPagination() {
        return pagination;
    }

    public void setPagination(PaginationResponseBody pagination) {
        this.pagination = pagination;
    }

    public GeaPackagesResponse(List<GeaPackagesWithClasses> geaPackages, PaginationResponseBody pagination) {
        this.geaPackages = geaPackages;
        this.pagination = pagination;
    }
}
