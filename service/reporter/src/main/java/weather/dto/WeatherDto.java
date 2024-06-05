package weather.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class WeatherDto {

    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    private @NonNull TemperatureDto temperature;
}
