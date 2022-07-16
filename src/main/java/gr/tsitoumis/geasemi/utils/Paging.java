package gr.tsitoumis.geasemi.utils;


public class Paging {
    private int pageNumber;
    private int pageSize;

    public Paging(int pageNumber, int pageSize) throws Exception {
        if (pageSize > 100) {
            throw new Exception("Page size can not be over 100");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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