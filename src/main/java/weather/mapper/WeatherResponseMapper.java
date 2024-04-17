package weather.mapper;

import lombok.NonNull;
import weather.model.WeatherDto;
import weather.response.WeatherResponse;

public interface WeatherResponseMapper {

    WeatherResponse mapWeatherDtoInWeatherResponse(@NonNull WeatherDto weatherDto);
}
