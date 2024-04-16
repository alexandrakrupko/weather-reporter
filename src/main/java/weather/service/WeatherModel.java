package weather.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

public interface WeatherModel {

    @Data
    @NoArgsConstructor
    class Create {
        private @NonNull String city;
        private @NonNull LocalDateTime localDateTime;
    }

    @Data
    @NoArgsConstructor
    class Read {
        private @NonNull String city;
        private @NonNull LocalDateTime localDateTime;
        private @NonNull TemperatureModel.Read readTemperature;
    }
}
