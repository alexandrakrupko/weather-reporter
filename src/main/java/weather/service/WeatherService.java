package weather.service;

import lombok.NonNull;
import weather.model.WeatherDto;

import java.util.Optional;

public interface WeatherService {

    Optional<WeatherDto> getByCity(@NonNull String city);
}
