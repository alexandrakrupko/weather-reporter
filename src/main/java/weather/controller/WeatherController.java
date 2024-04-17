package weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather.exception.ControllerException;
import weather.mapper.WeatherResponseMapper;
import weather.model.WeatherDto;
import weather.response.WeatherResponse;
import weather.service.WeatherService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherResponseMapper weatherResponseMapper;

    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherResponse> getByCity(@PathVariable String city) throws ControllerException {

        Optional<WeatherDto> optionalWeatherDto = weatherService.getByCity(city);
        return optionalWeatherDto
                .map(it -> ResponseEntity.ok(weatherResponseMapper.mapWeatherDtoInWeatherResponse(it)))
                .orElseThrow(() -> new ControllerException("Fail to process request", HttpStatus.NOT_FOUND));
    }
}
