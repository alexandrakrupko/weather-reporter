package weather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weather.IntegrationTest;
import weather.repository.WeatherRepository;
import weather.response.WeatherResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static weather.util.WeatherTestUtils.createWeather;

@SpringBootTest
class WeatherServiceImplIntegrationTest extends IntegrationTest {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherService weatherService;

    @Test
    @DisplayName("should return Weather for city from database")
    public void shouldReturnWeatherForCityFromDatabase() {
        // given
        weatherRepository.save(createWeather("minsk", 3.0f));
        weatherRepository.save(createWeather("grodno", 4.0f));

        // when
        Optional<WeatherResponse> optionalWeatherResponse = weatherService.getByCity("minsk");

        // then
        assertTrue(optionalWeatherResponse.isPresent());

        WeatherResponse weatherResponse = optionalWeatherResponse.get();
        assertEquals("Minsk", weatherResponse.getCity());
        assertEquals(3.0f, weatherResponse.getTemperature().getActual(), 0.001);
    }
}