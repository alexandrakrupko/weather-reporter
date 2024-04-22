package weather.exception;

public class ExternalServiceInvokeException extends RuntimeException {

    public ExternalServiceInvokeException(String message, Throwable cause) {
        super(message, cause);
    }
}