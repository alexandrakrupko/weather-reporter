package weather.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import weather.response.ErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleBadRequestException(BadRequestException e) {
        log.error("Bad request to external service", e);
        return ErrorResponse.builder()
                .message("Bad request to external service")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(FailedRequestException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse handleFailedRequestException(FailedRequestException e) {
        log.error("Failed request to external service", e);
        return ErrorResponse.builder()
                .message("Failed request to external service")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_GATEWAY)
                .build();
    }

    @ExceptionHandler(WeatherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleWeatherNotFoundException(WeatherNotFoundException e) {
        log.error("City '{}' not found", e.getCity(), e);
        return ErrorResponse.builder()
                .message("City '%s' not found".formatted(e.getCity()))
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleException(AccessDeniedException e) throws AccessDeniedException {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("Internal server error", e);
        return ErrorResponse.builder()
                .message("Internal server error")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
