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
import weather.service.WeatherService;

import java.util.Optional;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static weather.util.WeatherTestUtils.createWeatherResponse;


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
        when(weatherService.getByCity("city"))
                .thenReturn(Optional.of(createWeatherResponse("city", 1.3f)));

        mockMvc.perform(get("/weather/" + "city")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.temperature").isMap())
                .andExpect(jsonPath("$.temperature", aMapWithSize(3)))
                .andExpect(jsonPath("$.temperature.actual", is(1.3)));
    }

    @Test
    @DisplayName("should throw WeatherNotFoundException when service returns empty optional")
    public void shouldThrowWeatherNotFoundExceptionWhenServiceReturnsEmptyOptional() throws Exception {
        // when, then
        when(weatherService.getByCity("city")).thenReturn(Optional.empty());

        mockMvc.perform(get("/weather/" + "city")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    Exception resolvedException = result.getResolvedException();
                    assertInstanceOf(WeatherNotFoundException.class, resolvedException);
                    assertEquals("City not found", resolvedException.getMessage());
                });
    }
}