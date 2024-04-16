package weather.service;

import lombok.NonNull;

public interface WeatherService {

    WeatherModel.Read create(@NonNull WeatherModel.Create createWeather);
}
