package weather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import weather.exception.WeatherNotFoundException;
import weather.response.TemperatureResponse;
import weather.response.WeatherResponse;
import weather.service.WeatherService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc(addFilters = false)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherService weatherService;

    @Test
    @DisplayName("should return WeatherResponse")
    public void shouldReturnWeatherResponse() throws Exception {
        // when, then
        when(weatherService.getByCity(Tool.city)).thenReturn(Optional.of(Tool.weatherResponse));

        mockMvc.perform(get("/weather/" + Tool.city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city", is(Tool.city)))
                .andExpect(jsonPath("$.timestamp", is(Tool.timestamp.toString())))
                .andExpect(jsonPath("$.temperature").isMap())
                .andExpect(jsonPath("$.temperature.actual", is(1.1)))
                .andExpect(jsonPath("$.temperature.feelsLike", is(1.2)))
                .andExpect(jsonPath("$.temperature.rainfall", is(Tool.temperatureResponse.getRainfall())));
    }

    @Test
    @DisplayName("should throw WeatherNotFoundException when service returns empty optional")
    public void shouldThrowWeatherNotFoundExceptionWhenServiceReturnsEmptyOptional() throws Exception {
        // when, then
        when(weatherService.getByCity(Tool.city)).thenReturn(Optional.empty());

        mockMvc.perform(get("/weather/" + Tool.city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    Exception resolvedException = result.getResolvedException();
                    assertInstanceOf(WeatherNotFoundException.class, resolvedException);
                    assertEquals("City not found", resolvedException.getMessage());
                });
    }

    private static class Tool {
        private static final String city = "city";
        private static final TemperatureResponse temperatureResponse = TemperatureResponse.builder()
                .actual(1.1f)
                .feelsLike(1.2f)
                .rainfall("Sunny")
                .build();
        private static final LocalDateTime timestamp = LocalDateTime.now().withNano(0);
        private static final WeatherResponse weatherResponse = WeatherResponse.builder()
                .city(city)
                .timestamp(timestamp)
                .temperature(temperatureResponse)
                .build();
    }
}