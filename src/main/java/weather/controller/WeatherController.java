package weather.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    @PostMapping("/weather")
    public WeatherResponse.Read create(@NonNull @RequestBody WeatherRequest.Create createWeather) {
        WeatherResponse.Read readWeather = new WeatherResponse.Read();
        readWeather.setCity("Minsk");
        readWeather.setLocalDateTime(createWeather.getLocalDateTime());

        TemperatureResponse.Read readTemperature = new TemperatureResponse.Read();
        readTemperature.setActual(13.2f);
        readTemperature.setFeelsLike(11.2f);
        readTemperature.setRainfall("Rain");
        readWeather.setReadTemperature(readTemperature);

        return readWeather;
    }
}
