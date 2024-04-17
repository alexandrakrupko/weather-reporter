package weather.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Override
    public Optional<WeatherModel.Read> getByCity(@NonNull String city) {
        WeatherModel.Read readWeather = new WeatherModel.Read();
        readWeather.setCity(city);
        readWeather.setLocalDateTime(LocalDateTime.now());

        TemperatureModel.Read readTemperature = new TemperatureModel.Read();
        readTemperature.setActual(13.2f);
        readTemperature.setFeelsLike(11.2f);
        readTemperature.setRainfall("Rain");
        readWeather.setReadTemperature(readTemperature);

        return Optional.of(readWeather);
    }
}
