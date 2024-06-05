package weather.exception;

/**
 * This exception is thrown when external service returns 5xx status.
 */
public class FailedRequestException extends RuntimeException {

    public FailedRequestException(String message) {
        super(message);
    }

    public FailedRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}