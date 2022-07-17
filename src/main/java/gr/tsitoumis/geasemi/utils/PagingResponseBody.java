package gr.tsitoumis.geasemi.utils;

public class PagingResponseBody {
    private int current;
    private int end;
    private int size;

    public PagingResponseBody(int current, int size, int allPages) {
        // Api pagination starts from zero.
        this.end = allPages - 1;
        this.current = current;
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
