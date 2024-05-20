package weather.util;

import weather.model.Temperature;
import weather.model.Weather;
import weather.response.TemperatureResponse;
import weather.response.WeatherResponse;

import java.time.LocalDateTime;

public class WeatherTestUtils {

    public static Weather createWeather(String city, Float temperatureActual) {
        return Weather.builder()
                .city(city)
                .timestamp(LocalDateTime.now())
                .temperature(Temperature.builder()
                        .actual(temperatureActual)
                        .rainfall("Sunny")
                        .feelsLike(1.0f)
                        .build())
                .build();
    }

    public static WeatherResponse createWeatherResponse(String city, Float temperatureActual) {
        return WeatherResponse.builder()
                .city(city)
                .timestamp(LocalDateTime.now().withNano(0))
                .temperature(TemperatureResponse.builder()
                        .actual(temperatureActual)
                        .feelsLike(2.2f)
                        .rainfall("Snow")
                        .build())
                .build();
    }
}
