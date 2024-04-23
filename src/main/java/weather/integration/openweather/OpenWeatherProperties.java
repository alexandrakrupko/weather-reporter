package weather.integration.openweather;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("integration.openweather")
@Validated
@Getter
@Setter
class OpenWeatherProperties {

    @NotNull(message = "integration.openweather.api-key property is not specified")
    private String apiKey;

    private String units;
}
