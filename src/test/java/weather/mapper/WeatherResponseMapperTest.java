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
import weather.response.TemperatureResponse;
import weather.response.WeatherResponse;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherResponseMapperTest {

    @Mock
    private TemperatureResponseMapper temperatureResponseMapper;
    @InjectMocks
    private WeatherResponseMapper weatherResponseMapper;

    @Test
    @DisplayName("should map WeatherDto in WeatherResponse")
    public void shouldMapWeatherDtoInWeatherResponse() {
        // given
        TemperatureDto mockTemperatureDto = mock(TemperatureDto.class);
        TemperatureResponse mockTemperatureResponse = mock(TemperatureResponse.class);
        when(temperatureResponseMapper.mapTemperatureDtoInTemperatureResponse(mockTemperatureDto))
                .thenReturn(mockTemperatureResponse);

        LocalDateTime timestamp = LocalDateTime.now();
        WeatherDto weatherDto = WeatherDto.builder()
                .city("city")
                .timestamp(timestamp)
                .temperature(mockTemperatureDto)
                .build();

        // when
        WeatherResponse weatherResponse = weatherResponseMapper.mapWeatherDtoInWeatherResponse(weatherDto);

        // then
        verify(temperatureResponseMapper, times(1))
                .mapTemperatureDtoInTemperatureResponse(mockTemperatureDto);
        assertNotNull(weatherResponse);
        assertEquals("City", weatherResponse.getCity());
        assertEquals(timestamp, weatherResponse.getTimestamp());
        assertEquals(mockTemperatureResponse, weatherResponse.getTemperature());
    }

    @Test
    @DisplayName("should map Weather in WeatherResponse")
    public void shouldMapWeatherInWeatherResponse() {
        // given
        Temperature mockTemperature = mock(Temperature.class);
        TemperatureResponse mockTemperatureResponse = mock(TemperatureResponse.class);
        when(temperatureResponseMapper.mapTemperatureInTemperatureResponse(mockTemperature))
                .thenReturn(mockTemperatureResponse);

        LocalDateTime timestamp = LocalDateTime.now();
        Weather weather = Weather.builder()
                .city("city")
                .timestamp(timestamp)
                .temperature(mockTemperature)
                .build();

        // when
        WeatherResponse weatherResponse = weatherResponseMapper.mapWeatherInWeatherResponse(weather);

        // then
        verify(temperatureResponseMapper, times(1))
                .mapTemperatureInTemperatureResponse(mockTemperature);
        assertNotNull(weatherResponse);
        assertEquals("City", weatherResponse.getCity());
        assertEquals(timestamp, weatherResponse.getTimestamp());
        assertEquals(mockTemperatureResponse, weatherResponse.getTemperature());
    }
}