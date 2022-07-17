package gr.tsitoumis.geasemi.utils;


import org.springframework.http.HttpStatus;

public class Pagination {
    private int pageNumber;
    private int pageSize;

    public Pagination(int pageNumber, int pageSize) throws Exception {

        if (pageNumber < 0) {
            throw new GeaSemiException("Page number can not be under 0", HttpStatus.BAD_REQUEST);
        }

        if (pageSize < 1) {
            throw new GeaSemiException("Page size can not be under 1", HttpStatus.BAD_REQUEST);
        }

        if (pageSize > 100) {
            throw new GeaSemiException("Page size can not be over 100", HttpStatus.BAD_REQUEST);
        }

        this.pageSize = pageSize;

        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}