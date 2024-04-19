package weather.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class TemperatureDto {

    private @NonNull Float actual;
    private @NonNull Float feelsLike;
    private @NonNull String rainfall;
}
