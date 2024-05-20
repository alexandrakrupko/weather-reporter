package weather.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import weather.IntegrationTest;
import weather.model.Weather;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static weather.util.WeatherTestUtils.createWeather;

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
        entityManager.persistAndFlush(createWeather("City", 5.2f));

        // when
        Optional<Weather> optionalWeather = weatherRepository.findByCityAndCurrentDate("City");

        // then
        assertTrue(optionalWeather.isPresent());

        Weather weather = optionalWeather.get();
        assertEquals("City", weather.getCity());
        assertEquals(5.2f, weather.getTemperature().getActual());
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