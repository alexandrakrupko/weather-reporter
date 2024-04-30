package weather.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class TemperatureResponse implements Serializable {

    private @NonNull Float actual;
    private @NonNull Float feelsLike;
    private @NonNull String rainfall;
}
