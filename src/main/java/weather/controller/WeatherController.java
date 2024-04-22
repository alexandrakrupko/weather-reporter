package weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather.exception.WeatherNotFoundException;
import weather.response.WeatherResponse;
import weather.service.WeatherService;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherResponse> getByCity(@PathVariable String city) {
        return weatherService.getByCity(city)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new WeatherNotFoundException("City not found: '%s'".formatted(city)));
    }
}