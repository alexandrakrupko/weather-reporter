package weather.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weather.api.dto.TemperatureDto;
import weather.model.Temperature;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureDtoMapperTest {

    @Test
    @DisplayName("should map Temperature in TemperatureDto")
    public void mapTemperatureInTemperatureDto() {
        // given
        Temperature temperature = Temperature.builder()
                .actual(1.3f)
                .feelsLike(1.5f)
                .rainfall("Sunny")
                .build();
        TemperatureDtoMapper temperatureDtoMapper = new TemperatureDtoMapper();

        // when
        TemperatureDto temperatureDto = temperatureDtoMapper.mapTemperatureInTemperatureDto(temperature);

        // then
        assertNotNull(temperatureDto);
        assertEquals(1.3f, temperatureDto.getActual());
        assertEquals(1.5f, temperatureDto.getFeelsLike());
        assertEquals("Sunny", temperatureDto.getRainfall());
    }
}