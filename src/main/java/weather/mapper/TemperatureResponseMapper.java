package weather.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.model.TemperatureDto;
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
}
