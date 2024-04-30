package weather.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class WeatherResponse implements Serializable {

    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    private @NonNull TemperatureResponse temperature;
}
