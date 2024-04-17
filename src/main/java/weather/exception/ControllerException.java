package weather.exception;

import org.springframework.http.HttpStatus;

public class ControllerException extends Exception {

    private final HttpStatus status;

    public ControllerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
