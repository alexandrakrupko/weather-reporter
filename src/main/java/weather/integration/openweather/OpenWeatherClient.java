package weather.integration.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import weather.dto.WeatherDto;
import weather.exception.CityNotFoundException;
import weather.exception.RequestProcessingException;
import weather.integration.WeatherClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class OpenWeatherClient implements WeatherClient {

    private final OpenWeatherProperties openWeatherProperties;
    private final OpenWeatherJsonParser openWeatherJsonParser;

    @Override
    public Optional<WeatherDto> getByCity(@NonNull String city) {
        String apiKey = openWeatherProperties.getApiKey();
        String url = String.join("",
                "https://api.openweathermap.org/data/2.5/weather?q=", city,
                "&appid=", apiKey,
                "&units=", openWeatherProperties.getUnits()
        );

        ResponseEntity<String> response;
        try {
            response = RestClient.create()
                    .get()
                    .uri(url)
                    .header("Accept", "application/json")
                    .retrieve()
                    .toEntity(String.class);
        } catch (RuntimeException e) {
            throw new CityNotFoundException(
                    "Fail to get weather data for city \"%s\"".formatted(city), e);
        }

        if (!response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)) || !response.hasBody()) {
            throw new CityNotFoundException("Fail to get weather data for city \"%s\"".formatted(city));
        }

        try {
            return Optional.of(openWeatherJsonParser.toWeatherDto(response.getBody()));
        } catch (JsonProcessingException e) {
            throw new RequestProcessingException("Fail to get weather data", e);
        }
    }
}
