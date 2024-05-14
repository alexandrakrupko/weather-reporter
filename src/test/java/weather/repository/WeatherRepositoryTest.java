package weather.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import weather.IntegrationTest;
import weather.model.Temperature;
import weather.model.Weather;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WeatherRepositoryTest extends IntegrationTest {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("should return Weather when exists")
    public void shouldReturnWeatherWhenExists() {
        // given
        Temperature temperature = Temperature.builder()
                .actual(1.2f)
                .feelsLike(2.2f)
                .rainfall("Snow")
                .build();
        String city = "City";
        Weather weather = Weather.builder()
                .city(city)
                .timestamp(LocalDateTime.now())
                .temperature(temperature)
                .build();
        entityManager.persistAndFlush(weather);

        // when
        Optional<Weather> optionalWeather = weatherRepository.findByCityAndCurrentDate(city);

        // then
        assertTrue(optionalWeather.isPresent());
        assertEquals(city, optionalWeather.get().getCity());
        assertEquals(temperature, optionalWeather.get().getTemperature());
    }

    @Test
    @DisplayName("should return empty optional when weather does not exist")
    public void shouldReturnEmptyOptionalWhenWeatherDoesNotExist() {
        // given
        String city = "City";

        // when
        Optional<Weather> optionalWeather = weatherRepository.findByCityAndCurrentDate(city);

        // then
        assertTrue(optionalWeather.isEmpty());
    }
}