package weather.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.api.dto.TemperatureDto;
import weather.api.dto.WeatherDto;
import weather.model.Weather;

import static weather.util.StringUtils.capitalizeFirstLetter;

@Component
@RequiredArgsConstructor
public class WeatherDtoMapper {

    private final TemperatureDtoMapper temperatureDtoMapper;

    public WeatherDto mapWeatherInWeatherDto(@NonNull Weather weather) {
        TemperatureDto temperatureDto = temperatureDtoMapper
                .mapTemperatureInTemperatureDto(weather.getTemperature());
        return WeatherDto.builder()
                .city(capitalizeFirstLetter(weather.getCity()))
                .timestamp(weather.getTimestamp())
                .temperature(temperatureDto)
                .build();
    }
}
