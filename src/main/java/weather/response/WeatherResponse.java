package weather.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class WeatherResponse {

    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    private @NonNull TemperatureResponse temperature;
}
