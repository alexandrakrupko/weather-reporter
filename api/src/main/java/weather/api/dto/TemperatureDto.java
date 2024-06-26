package weather.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureDto {

    private @NonNull Float actual;
    private @NonNull Float feelsLike;
    private @NonNull String rainfall;
}
