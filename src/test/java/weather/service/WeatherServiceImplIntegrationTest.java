package weather.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weather.IntegrationTest;
import weather.model.Temperature;
import weather.model.Weather;
import weather.repository.WeatherRepository;
import weather.response.WeatherResponse;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static weather.util.StringUtils.capitalizeFirstLetter;

@SpringBootTest
class WeatherServiceImplIntegrationTest extends IntegrationTest {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        weatherRepository.save(Tool.firstWeather);
        weatherRepository.save(Tool.secondWeather);
    }

    @Test
    @DisplayName("should return Weather for city from database")
    public void shouldReturnWeatherForCityFromDatabase() {
        // when
        Optional<WeatherResponse> optionalWeatherResponse = weatherService.getByCity(Tool.firstCity);

        // then
        assertTrue(optionalWeatherResponse.isPresent());
        assertEquals(capitalizeFirstLetter(Tool.firstCity), optionalWeatherResponse.get().getCity());
        assertEquals(Tool.firstActual, optionalWeatherResponse.get().getTemperature().getActual(), 0.001);
    }

    private static class Tool {
        private static final String firstCity = "first_city";
        private static final String secondCity = "second_city";
        private static final float firstActual = 1.1f;
        private static final float secondActual = 2.1f;
        private static final Temperature firstTemperature = Temperature.builder()
                .actual(firstActual)
                .feelsLike(1.2f)
                .rainfall("Sunny")
                .build();
        private static final Temperature secondTemperature = Temperature.builder()
                .actual(secondActual)
                .feelsLike(2.2f)
                .rainfall("Snow")
                .build();
        private static final Weather firstWeather = Weather.builder()
                .city(firstCity)
                .timestamp(LocalDateTime.now())
                .temperature(firstTemperature)
                .build();
        private static final Weather secondWeather = Weather.builder()
                .city(secondCity)
                .timestamp(LocalDateTime.now())
                .temperature(secondTemperature)
                .build();
    }

}