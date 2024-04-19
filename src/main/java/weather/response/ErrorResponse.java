package weather.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private @NonNull String message;
    private @NonNull HttpStatus status;
    private @NonNull LocalDateTime timestamp;
}
