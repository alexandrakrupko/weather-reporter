package weather.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.model.WeatherDto;
import weather.response.TemperatureResponse;
import weather.response.WeatherResponse;

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
}
