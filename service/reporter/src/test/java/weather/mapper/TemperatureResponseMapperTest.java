package weather.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weather.api.dto.TemperatureDto;
import weather.model.Temperature;
import weather.response.TemperatureResponse;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureResponseMapperTest {

    private static TemperatureResponseMapper temperatureResponseMapper;

    @BeforeAll
    public static void setTemperatureResponseMapper() {
        temperatureResponseMapper = new TemperatureResponseMapper();
    }

    @Test
    @DisplayName("should map TemperatureDto in TemperatureResponse")
    public void shouldMapTemperatureDtoInTemperatureResponse() {
        // given
        TemperatureDto temperatureDto = TemperatureDto.builder()
                .actual(1.7f)
                .feelsLike(1.8f)
                .rainfall("Cloudy")
                .build();

        // when
        TemperatureResponse temperatureResponse = temperatureResponseMapper
                .mapTemperatureDtoInTemperatureResponse(temperatureDto);

        // then
        assertNotNull(temperatureResponse);
        assertEquals(1.7f, temperatureResponse.getActual());
        assertEquals(1.8f, temperatureResponse.getFeelsLike());
        assertEquals("Cloudy", temperatureResponse.getRainfall());
    }

    @Test
    @DisplayName("should map Temperature in TemperatureResponse")
    public void shouldMapTemperatureInTemperatureResponse() {
        // given
        Temperature temperature = Temperature.builder()
                .actual(1.7f)
                .feelsLike(1.8f)
                .rainfall("Cloudy")
                .build();

        // when
        TemperatureResponse temperatureResponse = temperatureResponseMapper
                .mapTemperatureInTemperatureResponse(temperature);

        // then
        assertNotNull(temperatureResponse);
        assertEquals(1.7f, temperatureResponse.getActual());
        assertEquals(1.8f, temperatureResponse.getFeelsLike());
        assertEquals("Cloudy", temperatureResponse.getRainfall());
    }
}