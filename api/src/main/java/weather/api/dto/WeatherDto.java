package weather.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class WeatherDto {

    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    private @NonNull TemperatureDto temperature;
}
