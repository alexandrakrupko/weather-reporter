package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.integration.WeatherClient;
import weather.mapper.WeatherResponseMapper;
import weather.response.WeatherResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherResponseMapper weatherResponseMapper;

    @Override
    public Optional<WeatherResponse> getByCity(@NonNull String city) {
        return weatherClient.getByCity(city)
                .map(weatherResponseMapper::mapWeatherDtoInWeatherResponse);
    }
}
