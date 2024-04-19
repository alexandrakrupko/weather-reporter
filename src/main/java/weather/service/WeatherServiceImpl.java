package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.mapper.WeatherResponseMapper;
import weather.model.TemperatureDto;
import weather.model.WeatherDto;
import weather.response.WeatherResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class WeatherServiceImpl implements WeatherService {

    private final WeatherResponseMapper weatherResponseMapper;

    @Override
    public Optional<WeatherResponse> getByCity(@NonNull String city) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCity(city);
        weatherDto.setTimestamp(LocalDateTime.now());

        TemperatureDto temperatureDto = new TemperatureDto();
        temperatureDto.setActual(13.2f);
        temperatureDto.setFeelsLike(11.2f);
        temperatureDto.setRainfall("Rain");
        weatherDto.setTemperature(temperatureDto);

        WeatherResponse weatherResponse = weatherResponseMapper.mapWeatherDtoInWeatherResponse(weatherDto);

        return Optional.of(weatherResponse);
    }
}
