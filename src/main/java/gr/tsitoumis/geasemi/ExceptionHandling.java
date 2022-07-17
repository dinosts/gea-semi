package gr.tsitoumis.geasemi;

import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GeaSemiException;
import gr.tsitoumis.geasemi.utils.GitTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptions(Exception exception, WebRequest webRequest) {

        // Guarding endpoints that use git
        String uri = ((ServletWebRequest) webRequest).getRequest().getRequestURI();

        if (uri.contains("run")) {
            try {
                Commands.deleteProject(GitTools.getProjectName(webRequest.getParameter("url")));
            } catch (Exception e) {
                System.out.println("Failed to delete project");
            }
        }

        HttpStatus httpStatus;

        if (exception instanceof GeaSemiException) {
            httpStatus = ((GeaSemiException) exception).getHttpStatus();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        String message = Objects.requireNonNullElse(exception.getMessage(), "Internal server error");

        ExceptionResponse response = new ExceptionResponse(message, httpStatus);

        return new ResponseEntity(response, httpStatus);
    }
}

class ExceptionResponse {
    private HttpStatus error;
    private String message;

    ExceptionResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.error = httpStatus;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}