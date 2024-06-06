package weather.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.api.dto.WeatherDto;
import weather.model.Temperature;
import weather.model.Weather;

@Component
@RequiredArgsConstructor
public class WeatherMapper {

    private final TemperatureMapper temperatureMapper;

    public Weather mapWeatherDtoInWeather(@NonNull WeatherDto weatherDto) {
        Temperature temperature = temperatureMapper
                .mapTemperatureDtoInTemperature(weatherDto.getTemperature());
        return Weather.builder()
                .city(weatherDto.getCity().toLowerCase())
                .timestamp(weatherDto.getTimestamp())
                .temperature(temperature)
                .build();
    }
}
