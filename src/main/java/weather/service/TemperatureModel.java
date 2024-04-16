package weather.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public interface TemperatureModel {

    @Data
    @NoArgsConstructor
    class Read {
        private @NonNull Float actual;
        private @NonNull Float feelsLike;
        private @NonNull String rainfall;

    }
}
