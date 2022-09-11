package gr.tsitoumis.geasemi;

import gr.tsitoumis.geasemi.utils.GeaSemiException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    private static Logger logger = LogManager.getLogger(ExceptionHandling.class);

    @ExceptionHandler(Exception.class)

    public ResponseEntity handleExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus httpStatus;
        String message = "Internal Server Error";


        if (exception instanceof GeaSemiException) {
            httpStatus = ((GeaSemiException) exception).getHttpStatus();
            message = exception.getMessage();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ExceptionResponse response = new ExceptionResponse(message, httpStatus);


        logger.error(MDC.get("Slf4jMDCFilter.UUID"), exception);


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

    @Override
    public String toString() {
        return error + " - " + message;
    }
}