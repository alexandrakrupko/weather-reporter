package weather.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import weather.model.Weather;
import weather.repository.WeatherRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public void save(@NonNull Weather weather) {
        Weather savedWeather = weatherRepository.save(weather);
        log.info("Weather saved to database: {}", savedWeather);
    }
}