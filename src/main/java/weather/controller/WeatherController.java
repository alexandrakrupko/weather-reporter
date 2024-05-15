package weather.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather.exception.WeatherNotFoundException;
import weather.response.WeatherResponse;
import weather.security.JwtAuthentication;
import weather.service.WeatherService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather/{city}")
    @JwtAuthentication
    public ResponseEntity<WeatherResponse> getByCity(@PathVariable String city) {
        log.info("Weather requested for city '{}'", city);
        return weatherService.getByCity(city)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new WeatherNotFoundException("City not found", city));
    }
}