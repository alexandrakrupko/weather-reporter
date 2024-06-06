package weather.integration;

import lombok.NonNull;
import weather.api.dto.WeatherDto;

import java.util.Optional;

public interface WeatherClient {

    Optional<WeatherDto> getByCity(@NonNull String city);
}
