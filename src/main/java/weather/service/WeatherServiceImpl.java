package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import weather.dto.WeatherDto;
import weather.integration.WeatherClient;
import weather.mapper.WeatherDtoMapper;
import weather.mapper.WeatherMapper;
import weather.mapper.WeatherResponseMapper;
import weather.model.Weather;
import weather.repository.WeatherRepository;
import weather.response.WeatherResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherClient weatherClient;
    private final WeatherResponseMapper weatherResponseMapper;
    private final WeatherMapper weatherMapper;
    private final WeatherDtoMapper weatherDtoMapper;
    private final TransactionTemplate transactionTemplate;

    @Override
    @Cacheable(value = "weatherCache", key = "#city.toLowerCase() + T(java.time.LocalDate).now()")
    public Optional<WeatherResponse> getByCity(@NonNull String city) {
        Optional<Weather> optionalWeather = weatherRepository.findByCityAndCurrentDate(city.toLowerCase());

        if (optionalWeather.isPresent()) {
            return Optional.of(weatherResponseMapper.mapWeatherInWeatherResponse(optionalWeather.get()));
        }

        Optional<WeatherDto> optionalWeatherDto = weatherClient.getByCity(city);
        if (optionalWeatherDto.isPresent()) {
            WeatherDto savedWeatherDto = save(optionalWeatherDto.get());
            return Optional.of(weatherResponseMapper.mapWeatherDtoInWeatherResponse(savedWeatherDto));
        }

        return Optional.empty();
    }

    private WeatherDto save(@NonNull WeatherDto weatherDto) {
        return transactionTemplate.execute(status -> {
            Weather weather = weatherRepository.save(weatherMapper.mapWeatherDtoInWeather(weatherDto));
            return weatherDtoMapper.mapWeatherInWeatherDto(weather);
        });
    }
}
