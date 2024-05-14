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
class WeatherDtoMapperTest {

    @Mock
    private TemperatureDtoMapper temperatureDtoMapper;
    @InjectMocks
    private WeatherDtoMapper weatherDtoMapper;

    @Test
    @DisplayName("should map Weather in WeatherDto")
    void shouldMapWeatherInWeatherDto() {
        // given
        Temperature mockTemperature = mock(Temperature.class);
        TemperatureDto mockTemperatureDto = mock(TemperatureDto.class);

        when(temperatureDtoMapper.mapTemperatureInTemperatureDto(mockTemperature))
                .thenReturn(mockTemperatureDto);

        LocalDateTime timestamp = LocalDateTime.now();
        Weather weather = Weather.builder()
                .city("minsk")
                .timestamp(timestamp)
                .temperature(mockTemperature)
                .build();

        // when
        WeatherDto weatherDto = weatherDtoMapper.mapWeatherInWeatherDto(weather);

        // then
        verify(temperatureDtoMapper, times(1)).mapTemperatureInTemperatureDto(mockTemperature);
        assertNotNull(weatherDto);
        assertEquals("Minsk", weatherDto.getCity());
        assertEquals(timestamp, weatherDto.getTimestamp());
        assertEquals(mockTemperatureDto, weatherDto.getTemperature());
    }
}