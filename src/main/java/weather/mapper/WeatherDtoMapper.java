package weather.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.dto.TemperatureDto;
import weather.dto.WeatherDto;
import weather.model.Weather;

@Component
@RequiredArgsConstructor
public class WeatherDtoMapper {

    private final TemperatureDtoMapper temperatureDtoMapper;

    public WeatherDto mapWeatherInWeatherDto(@NonNull Weather weather) {
        TemperatureDto temperatureDto = temperatureDtoMapper
                .mapTemperatureInTemperatureDto(weather.getTemperature());
        return WeatherDto.builder()
                .city(weather.getCity())
                .timestamp(weather.getTimestamp())
                .temperature(temperatureDto)
                .build();
    }
}
