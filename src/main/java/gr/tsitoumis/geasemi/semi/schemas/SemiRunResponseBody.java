package gr.tsitoumis.geasemi.semi.schemas;

public class SemiRunResponseBody {
    String message;

    public SemiRunResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
