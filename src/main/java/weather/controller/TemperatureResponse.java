package weather.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public interface TemperatureResponse {

    @Data
    @NoArgsConstructor
    class Read {
        private float actual;
        private float feelsLike;
        private @NonNull String rainfall;
    }
}
