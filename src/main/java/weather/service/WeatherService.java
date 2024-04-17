package weather.service;

import lombok.NonNull;

import java.util.Optional;

public interface WeatherService {

    Optional<WeatherModel.Read> getByCity(@NonNull String city);
}
