package weather.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

public interface WeatherRequest {

    @Data
    @NoArgsConstructor
    class Create {
        private @NonNull String city;
        private final LocalDateTime localDateTime = LocalDateTime.now();
    }
}
