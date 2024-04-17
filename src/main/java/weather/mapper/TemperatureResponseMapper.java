package weather.mapper;

import lombok.NonNull;
import weather.model.TemperatureDto;
import weather.response.TemperatureResponse;

public interface TemperatureResponseMapper {

    TemperatureResponse mapTemperatureDtoInTemperatureResponse(@NonNull TemperatureDto temperatureDto);
}
