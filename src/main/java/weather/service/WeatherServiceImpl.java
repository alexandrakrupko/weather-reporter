package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @SuppressWarnings("DataFlowIssue")
    public Optional<WeatherResponse> getByCity(@NonNull String city) {
        Optional<Weather> optionalWeather = weatherRepository.findByCityAndCurrentDate(city);

        if (optionalWeather.isPresent()) {
            return Optional.of(weatherResponseMapper.mapWeatherInWeatherResponse(optionalWeather.get()));
        }

        Optional<WeatherDto> optionalWeatherDto = weatherClient.getByCity(city);
        if (optionalWeatherDto.isPresent()) {
            WeatherDto savedWeatherDto = transactionTemplate
                    .execute((ignore) -> save(optionalWeatherDto.get()));
            return Optional.of(weatherResponseMapper.mapWeatherDtoInWeatherResponse(savedWeatherDto));
        }

        return Optional.empty();
    }

    @Override
    public WeatherDto save(@NonNull WeatherDto weatherDto) {
        Weather weather = weatherRepository.save(weatherMapper.mapWeatherDtoInWeather(weatherDto));
        return weatherDtoMapper.mapWeatherInWeatherDto(weather);
    }
}
