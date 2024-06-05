package weather.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.dto.TemperatureDto;
import weather.model.Temperature;

@Component
public class TemperatureDtoMapper {

    public TemperatureDto mapTemperatureInTemperatureDto(@NonNull Temperature temperature) {
        return TemperatureDto.builder()
                .actual(temperature.getActual())
                .feelsLike(temperature.getFeelsLike())
                .rainfall(temperature.getRainfall())
                .build();
    }
}
