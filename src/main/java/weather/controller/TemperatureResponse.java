package weather.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public interface TemperatureResponse {

    @Data
    @NoArgsConstructor
    class Read {
        private @NonNull Float actual;
        private @NonNull Float feelsLike;
        private @NonNull String rainfall;
    }
}
