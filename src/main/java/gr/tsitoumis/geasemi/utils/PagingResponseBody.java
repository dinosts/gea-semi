package gr.tsitoumis.geasemi.utils;

public class PagingResponseBody {
    private int currentPage;
    private int allPages;
    private int currentPageSize;

    public PagingResponseBody(int currentPage, int currentPageSize, int allItems) {
        this.allPages = (allItems / currentPageSize);

        if (allItems % currentPageSize > 0) {
            this.allPages++;
        }

        this.currentPage = currentPage;
        this.currentPageSize = currentPageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(int currentPageSize) {
        this.currentPageSize = currentPageSize;
    }
}
