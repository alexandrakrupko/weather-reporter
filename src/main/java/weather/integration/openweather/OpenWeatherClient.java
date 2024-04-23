package weather.integration.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import weather.dto.WeatherDto;
import weather.exception.BadRequestException;
import weather.exception.FailedRequestException;
import weather.integration.WeatherClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class OpenWeatherClient implements WeatherClient {

    private final OpenWeatherProperties openWeatherProperties;
    private final OpenWeatherJsonParser openWeatherJsonParser;

    @SuppressWarnings("DataFlowIssue")
    @Override
    public Optional<WeatherDto> getByCity(@NonNull String city) {
        String apiKey = openWeatherProperties.getApiKey();
        String url = String.join("",
                "https://api.openweathermap.org/data/2.5/weather?q=", city,
                "&appid=", apiKey,
                "&units=", openWeatherProperties.getUnits()
        );

        try {
            ResponseEntity<String> response = RestClient.create()
                    .get()
                    .uri(url)
                    .header("Accept", "application/json")
                    .retrieve()
                    .toEntity(String.class);

            if (!response.hasBody()) {
                throw new FailedRequestException("Unexpected empty response body");
            }

            return Optional.of(openWeatherJsonParser.toWeatherDto(response.getBody()));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            }
            throw new BadRequestException("Invalid request", e);
        } catch (HttpServerErrorException | JsonProcessingException e) {
            throw new FailedRequestException("Unexpected error", e);
        }
    }
}
