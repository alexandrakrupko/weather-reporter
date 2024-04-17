package weather.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class TemperatureResponse {

    private @NonNull Float actual;
    private @NonNull Float feelsLike;
    private @NonNull String rainfall;
}
