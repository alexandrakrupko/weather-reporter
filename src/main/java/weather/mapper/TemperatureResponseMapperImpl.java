package weather.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import weather.model.TemperatureDto;
import weather.response.TemperatureResponse;

@Component
class TemperatureResponseMapperImpl implements TemperatureResponseMapper {

    @Override
    public TemperatureResponse mapTemperatureDtoInTemperatureResponse(@NonNull TemperatureDto temperatureDto) {
        return TemperatureResponse.builder()
                .actual(temperatureDto.getActual())
                .feelsLike(temperatureDto.getFeelsLike())
                .rainfall(temperatureDto.getRainfall())
                .build();
    }
}
