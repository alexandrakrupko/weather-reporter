package weather.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import weather.service.WeatherModel;
import weather.service.WeatherService;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherRequestMapper weatherRequestMapper;
    private final WeatherResponseMapper weatherResponseMapper;

    @PostMapping("/weather")
    public WeatherResponse.Read create(@NonNull @RequestBody WeatherRequest.Create createWeather) {

        WeatherModel.Create createModel = weatherRequestMapper.toCreateModel(createWeather);
        WeatherModel.Read readModel = weatherService.create(createModel);
        return weatherResponseMapper.toReadResponse(readModel);
    }
}
