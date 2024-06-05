package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.info("Weather found in database for city '{}'", city);
            return Optional.of(weatherResponseMapper.mapWeatherInWeatherResponse(optionalWeather.get()));
        }

        Optional<WeatherDto> optionalWeatherDto = weatherClient.getByCity(city);
        if (optionalWeatherDto.isPresent()) {
            log.info("Weather retrieved from external service for city '{}'", city);
            WeatherDto savedWeatherDto = save(optionalWeatherDto.get());
            return Optional.of(weatherResponseMapper.mapWeatherDtoInWeatherResponse(savedWeatherDto));
        }

        log.warn("Fail to receive weather for city '{}'", city);
        return Optional.empty();
    }

    private WeatherDto save(@NonNull WeatherDto weatherDto) {
        return transactionTemplate.execute(status -> {
            Weather weather = weatherRepository.save(weatherMapper.mapWeatherDtoInWeather(weatherDto));
            log.info("Weather saved in database for city '{}'", weather.getCity());
            return weatherDtoMapper.mapWeatherInWeatherDto(weather);
        });
    }
}
