package weather.integration.openweather;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import weather.dto.WeatherDto;
import weather.exception.ExternalServiceInvokeException;
import weather.exception.ExternalServiceException;
import weather.exception.InvalidApiKeyException;
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

        Optional<WeatherDto> optionalWeatherDto;
        try {
            optionalWeatherDto = RestClient.create()
                    .get()
                    .uri(url)
                    .header("Accept", "application/json")
                    .exchange((request, response) -> {
                        HttpStatusCode statusCode = response.getStatusCode();
                        if (statusCode.isSameCodeAs(HttpStatusCode.valueOf(404))) {
                            return Optional.empty();
                        } else if (statusCode.isSameCodeAs(HttpStatusCode.valueOf(401))) {
                            throw new InvalidApiKeyException("Invalid API key provided");
                        } else if (statusCode.is5xxServerError()) {
                            throw new ExternalServiceException("Fail to get weather data");
                        } else {
                            return Optional.of(openWeatherJsonParser.toWeatherDto(response.getBody()));
                        }
                    });

            return optionalWeatherDto;
        } catch (RestClientResponseException e) {
            throw new ExternalServiceInvokeException("Fail to get weather data", e);
        }
    }
}
