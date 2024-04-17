package weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weather.service.WeatherModel;
import weather.service.WeatherService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherResponseMapper weatherResponseMapper;

    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherResponse.Read> getByCity(@PathVariable String city) {

        Optional<WeatherModel.Read> optionalReadModel = weatherService.getByCity(city);
        return optionalReadModel
                .map(it -> ResponseEntity.ok(weatherResponseMapper.toReadResponse(it)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
