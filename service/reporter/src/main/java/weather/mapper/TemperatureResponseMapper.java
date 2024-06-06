package weather.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.api.dto.TemperatureDto;
import weather.model.Temperature;
import weather.response.TemperatureResponse;

@Component
public class TemperatureResponseMapper {

    public TemperatureResponse mapTemperatureDtoInTemperatureResponse(@NonNull TemperatureDto temperatureDto) {
        return TemperatureResponse.builder()
                .actual(temperatureDto.getActual())
                .feelsLike(temperatureDto.getFeelsLike())
                .rainfall(temperatureDto.getRainfall())
                .build();
    }

    public TemperatureResponse mapTemperatureInTemperatureResponse(@NonNull Temperature temperature) {
        return TemperatureResponse.builder()
                .actual(temperature.getActual())
                .feelsLike(temperature.getFeelsLike())
                .rainfall(temperature.getRainfall())
                .build();
    }
}
