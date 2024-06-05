package weather.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import weather.dto.TemperatureDto;
import weather.dto.WeatherDto;
import weather.model.Temperature;
import weather.model.Weather;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherMapperTest {

    @Mock
    private TemperatureMapper temperatureMapper;
    @InjectMocks
    private WeatherMapper weatherMapper;

    @Test
    @DisplayName("should map WeatherDto in Weather")
    public void shouldMapWeatherDtoInWeather() {
        // given
        TemperatureDto mockTemperatureDto = mock(TemperatureDto.class);
        Temperature mockTemperature = mock(Temperature.class);

        when(temperatureMapper.mapTemperatureDtoInTemperature(mockTemperatureDto))
                .thenReturn(mockTemperature);

        LocalDateTime timestamp = LocalDateTime.now();
        WeatherDto weatherDto = WeatherDto.builder()
                .city("Minsk")
                .timestamp(timestamp)
                .temperature(mockTemperatureDto)
                .build();

        // when
        Weather weather = weatherMapper.mapWeatherDtoInWeather(weatherDto);

        // then
        verify(temperatureMapper, times(1)).mapTemperatureDtoInTemperature(mockTemperatureDto);
        assertNotNull(weather);
        assertEquals("minsk", weather.getCity());
        assertEquals(timestamp, weather.getTimestamp());
        assertEquals(mockTemperature, weather.getTemperature());
    }
}