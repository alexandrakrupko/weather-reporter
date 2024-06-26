package weather.integration.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import weather.api.dto.TemperatureDto;
import weather.api.dto.WeatherDto;
import weather.exception.FailedRequestException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static weather.util.OpenWeatherClientTestUtils.createUri;

@RestClientTest
@AutoConfigureMockRestServiceServer
class OpenWeatherClientTest {

    @MockBean
    private OpenWeatherProperties openWeatherProperties;
    @MockBean
    private OpenWeatherJsonParser openWeatherJsonParser;
    @SpyBean
    private RestClient.Builder restClientBuilder;
    @Autowired
    @SpyBean
    private OpenWeatherClient openWeatherClient;

    private MockRestServiceServer mockRestServiceServer;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockRestServiceServer = MockRestServiceServer.bindTo(restClientBuilder).build();
    }

    @Test
    @DisplayName("should invoke external api and return optional of WeatherDto")
    public void shouldInvokeExternalApiAndReturnOptionalOfWeatherDto() throws JsonProcessingException {
        // given
        when(openWeatherProperties.getApiKey()).thenReturn("apiKey");
        when(openWeatherProperties.getUnits()).thenReturn("units");

        TemperatureDto mockTemperatureDto = mock(TemperatureDto.class);
        WeatherDto weatherDto = WeatherDto.builder()
                .city("city")
                .timestamp(LocalDateTime.now())
                .temperature(mockTemperatureDto)
                .build();

        String response = objectMapper.writeValueAsString(weatherDto);
        mockRestServiceServer.expect(once(), requestTo(createUri("city", "apiKey", "units")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Accept", "application/json"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        when(openWeatherJsonParser.toWeatherDto(response)).thenReturn(weatherDto);

        // when
        Optional<WeatherDto> optionalWeatherDto = openWeatherClient.getByCity("city");

        // then
        mockRestServiceServer.verify();
        assertTrue(optionalWeatherDto.isPresent());
        assertEquals("city", optionalWeatherDto.get().getCity());
        assertEquals(mockTemperatureDto, optionalWeatherDto.get().getTemperature());
    }

    @Test
    @DisplayName("should throw FailedRequestException when response has no body")
    public void shouldThrowFailedRequestExceptionWhenResponseHasNoBody() {
        // given
        when(openWeatherProperties.getApiKey()).thenReturn("apiKey");
        when(openWeatherProperties.getUnits()).thenReturn("units");

        mockRestServiceServer.expect(once(), requestTo(createUri("city", "apiKey", "units")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Accept", "application/json"))
                .andRespond(withSuccess());

        // when, then
        Exception exception = assertThrows(
                FailedRequestException.class, () -> openWeatherClient.getByCity("city"));
        assertEquals("Unexpected empty response body", exception.getMessage());
        mockRestServiceServer.verify();
    }

    @TestConfiguration
    public static class Configuration {

        @Bean
        public RestClient.Builder restClientBuilder() {
            return RestClient.builder();
        }
    }
}