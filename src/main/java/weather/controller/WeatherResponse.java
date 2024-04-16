package weather.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

public interface WeatherResponse {

    @Data
    @NoArgsConstructor
    class Read {
        private @NonNull String city;
        private @NonNull LocalDateTime localDateTime;
        private @NonNull TemperatureResponse.Read readTemperature;
    }
}
