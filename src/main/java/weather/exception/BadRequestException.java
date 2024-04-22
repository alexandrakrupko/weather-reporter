package weather.exception;

/**
 * This exception is thrown when external service returns 4xx status.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}