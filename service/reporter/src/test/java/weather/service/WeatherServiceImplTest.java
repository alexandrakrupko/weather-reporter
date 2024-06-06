package weather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionTemplate;
import weather.api.dto.WeatherDto;
import weather.integration.WeatherClient;
import weather.mapper.WeatherResponseMapper;
import weather.model.Weather;
import weather.repository.WeatherRepository;
import weather.response.WeatherResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherClient weatherClient;
    @Mock
    private WeatherResponseMapper weatherResponseMapper;
    @Mock
    private TransactionTemplate transactionTemplate;
    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    @DisplayName("should return Weather from database")
    public void shouldReturnWeatherFromDatabase() {
        // given
        Weather mockWeather = mock(Weather.class);
        when(weatherRepository.findByCityAndCurrentDate("city")).thenReturn(Optional.of(mockWeather));

        WeatherResponse mockWeatherResponse = mock(WeatherResponse.class);
        when(weatherResponseMapper.mapWeatherInWeatherResponse(mockWeather)).thenReturn(mockWeatherResponse);

        // when
        Optional<WeatherResponse> optionalWeatherResponse = weatherService.getByCity("City");

        // then
        assertTrue(optionalWeatherResponse.isPresent());
        verify(weatherRepository, times(1)).findByCityAndCurrentDate("city");
        verify(weatherResponseMapper, times(1)).mapWeatherInWeatherResponse(mockWeather);
        assertEquals(mockWeatherResponse, optionalWeatherResponse.get());
    }

    @Test
    @DisplayName("should invoke WeatherClient, save Weather to database and return optional of Weather")
    public void shouldInvokeWeatherClientSaveWeatherToDatabaseAndReturnOptionalOfWeather() {
        // given
        when(weatherRepository.findByCityAndCurrentDate("city")).thenReturn(Optional.empty());
        WeatherDto mockWeatherDto = mock(WeatherDto.class);
        when(weatherClient.getByCity("City")).thenReturn(Optional.of(mockWeatherDto));

        when(transactionTemplate.execute(any())).thenReturn(mockWeatherDto);

        WeatherResponse mockWeatherResponse = mock(WeatherResponse.class);
        when(weatherResponseMapper.mapWeatherDtoInWeatherResponse(mockWeatherDto)).thenReturn(mockWeatherResponse);

        // when
        Optional<WeatherResponse> optionalWeatherResponse = weatherService.getByCity("City");

        // then
        assertTrue(optionalWeatherResponse.isPresent());
        verify(weatherRepository, times(1)).findByCityAndCurrentDate("city");
        verify(weatherResponseMapper, times(0)).mapWeatherInWeatherResponse(any(Weather.class));
        verify(weatherClient, times(1)).getByCity("City");
        verify(transactionTemplate, times(1)).execute(any());
        verify(weatherResponseMapper, times(1)).mapWeatherDtoInWeatherResponse(mockWeatherDto);
        assertEquals(mockWeatherResponse, optionalWeatherResponse.get());
    }

    @Test
    @DisplayName("should return empty optional when WeatherClient does not return weather")
    public void shouldReturnEmptyOptionalWhenWeatherClientDoesNotReturnWeather() {
        // given
        when(weatherRepository.findByCityAndCurrentDate("city")).thenReturn(Optional.empty());
        when(weatherClient.getByCity("City")).thenReturn(Optional.empty());

        // when
        Optional<WeatherResponse> optionalWeatherResponse = weatherService.getByCity("City");

        // then
        assertTrue(optionalWeatherResponse.isEmpty());
        verify(weatherRepository, times(1)).findByCityAndCurrentDate("city");
        verify(weatherResponseMapper, times(0)).mapWeatherInWeatherResponse(any(Weather.class));
        verify(weatherClient, times(1)).getByCity("City");
        verify(transactionTemplate, times(0)).execute(any());
        verify(weatherResponseMapper, times(0))
                .mapWeatherDtoInWeatherResponse(any(WeatherDto.class));
    }
}