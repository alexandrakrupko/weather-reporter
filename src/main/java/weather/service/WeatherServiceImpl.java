package weather.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import weather.model.TemperatureDto;
import weather.model.WeatherDto;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class WeatherServiceImpl implements WeatherService {

    @Override
    public Optional<WeatherDto> getByCity(@NonNull String city) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCity(city);
        weatherDto.setTimestamp(LocalDateTime.now());

        TemperatureDto temperatureDto = new TemperatureDto();
        temperatureDto.setActual(13.2f);
        temperatureDto.setFeelsLike(11.2f);
        temperatureDto.setRainfall("Rain");
        weatherDto.setTemperature(temperatureDto);

        return Optional.of(weatherDto);
    }
}
