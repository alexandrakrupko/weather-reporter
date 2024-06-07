package weather.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.api.dto.TemperatureDto;
import weather.model.Temperature;

@Component
public class TemperatureMapper {

    public Temperature mapTemperatureDtoInTemperature(@NonNull TemperatureDto temperatureDto) {
        return Temperature.builder()
                .actual(temperatureDto.getActual())
                .feelsLike(temperatureDto.getFeelsLike())
                .rainfall(temperatureDto.getRainfall())
                .build();
    }
}