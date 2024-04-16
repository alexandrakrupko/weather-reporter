package weather.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Override
    public WeatherModel.Read create(WeatherModel.@NonNull Create createModel) {
        WeatherModel.Read readWeather = new WeatherModel.Read();
        readWeather.setCity(createModel.getCity());
        readWeather.setLocalDateTime(createModel.getLocalDateTime());

        TemperatureModel.Read readTemperature = new TemperatureModel.Read();
        readTemperature.setActual(13.2f);
        readTemperature.setFeelsLike(11.2f);
        readTemperature.setRainfall("Rain");
        readWeather.setReadTemperature(readTemperature);

        return readWeather;
    }
}
