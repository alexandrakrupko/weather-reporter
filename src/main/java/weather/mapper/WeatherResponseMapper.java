package weather.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.dto.WeatherDto;
import weather.model.Weather;
import weather.response.TemperatureResponse;
import weather.response.WeatherResponse;

import static weather.util.StringUtils.capitalizeFirstLetter;

@Component
@RequiredArgsConstructor
public class WeatherResponseMapper {

    private final TemperatureResponseMapper temperatureResponseMapper;

    public WeatherResponse mapWeatherDtoInWeatherResponse(@NonNull WeatherDto weatherDto) {
        TemperatureResponse temperatureResponse = temperatureResponseMapper
                .mapTemperatureDtoInTemperatureResponse(weatherDto.getTemperature());
        return WeatherResponse.builder()
                .city(weatherDto.getCity())
                .timestamp(weatherDto.getTimestamp())
                .temperature(temperatureResponse)
                .build();
    }

    public WeatherResponse mapWeatherInWeatherResponse(@NonNull Weather weather) {
        TemperatureResponse temperatureResponse = temperatureResponseMapper
                .mapTemperatureInTemperatureResponse(weather.getTemperature());
        return WeatherResponse.builder()
                .city(capitalizeFirstLetter(weather.getCity()))
                .timestamp(weather.getTimestamp())
                .temperature(temperatureResponse)
                .build();
    }
}
