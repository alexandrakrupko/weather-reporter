package weather.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weather.dto.TemperatureDto;
import weather.model.Temperature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TemperatureMapperTest {

    @Test
    @DisplayName("should map TemperatureDto in Temperature")
    public void shouldMapTemperatureDtoInTemperature() {
        // given
        TemperatureDto temperatureDto = TemperatureDto.builder()
                .actual(1.3f)
                .feelsLike(1.5f)
                .rainfall("Sunny")
                .build();
        TemperatureMapper temperatureMapper = new TemperatureMapper();

        // when
        Temperature temperature = temperatureMapper.mapTemperatureDtoInTemperature(temperatureDto);

        // then
        assertNotNull(temperature);
        assertEquals(1.3f, temperature.getActual());
        assertEquals(1.5f, temperature.getFeelsLike());
        assertEquals("Sunny", temperature.getRainfall());
    }
}