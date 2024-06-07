package weather.service;

import lombok.NonNull;
import weather.model.Weather;

public interface WeatherService {

    void save(@NonNull Weather weather);
}