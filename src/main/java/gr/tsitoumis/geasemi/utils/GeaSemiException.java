package gr.tsitoumis.geasemi.utils;

import org.springframework.http.HttpStatus;

public class GeaSemiException extends Exception {
    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    GeaSemiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
