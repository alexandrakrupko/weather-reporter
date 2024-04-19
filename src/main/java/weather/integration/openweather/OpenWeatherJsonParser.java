package weather.integration.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.dto.TemperatureDto;
import weather.dto.WeatherDto;

import java.time.LocalDateTime;

@Component
class OpenWeatherJsonParser {

    public WeatherDto toWeatherDto(@NonNull String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        TemperatureDto temperatureDto = TemperatureDto.builder()
                .actual(jsonNode.at("/main/temp").floatValue())
                .feelsLike(jsonNode.at("/main/feels_like").floatValue())
                .rainfall(jsonNode.at("/weather/0/main").textValue())
                .build();

        return WeatherDto.builder()
                .city(jsonNode.get("name").textValue())
                .timestamp(LocalDateTime.now())
                .temperature(temperatureDto)
                .build();
    }
}
