package gr.tsitoumis.geasemi.gea.entities;

public class GeaRunResponseBody {
    String message;

    public GeaRunResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
