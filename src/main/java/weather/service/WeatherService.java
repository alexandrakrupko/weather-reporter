package weather.service;

import lombok.NonNull;
import weather.dto.WeatherDto;
import weather.response.WeatherResponse;

import java.util.Optional;

public interface WeatherService {

    Optional<WeatherResponse> getByCity(@NonNull String city);

    WeatherDto save(@NonNull WeatherDto weatherDto);
}
