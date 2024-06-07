package weather.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Temperature {

    private @NonNull Float actual;
    private @NonNull Float feelsLike;
    private @NonNull String rainfall;
}
