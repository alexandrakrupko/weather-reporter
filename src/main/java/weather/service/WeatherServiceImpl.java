package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.dto.WeatherDto;
import weather.exception.RequestProcessingException;
import weather.mapper.WeatherResponseMapper;
import weather.response.WeatherResponse;
import weather.integration.WeatherClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherResponseMapper weatherResponseMapper;

    @Override
    public Optional<WeatherResponse> getByCity(@NonNull String city) {
        Optional<WeatherDto> optionalWeatherDto = weatherClient.getByCity(city);
        return Optional.ofNullable(optionalWeatherDto
                .map(weatherResponseMapper::mapWeatherDtoInWeatherResponse)
                .orElseThrow(() -> new RequestProcessingException("Fail to get weather data")));
    }
}
