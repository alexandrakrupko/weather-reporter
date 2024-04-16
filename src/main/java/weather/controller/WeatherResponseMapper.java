package weather.controller;

import org.mapstruct.Mapper;
import weather.service.WeatherModel;

@Mapper(componentModel = "spring")
public interface WeatherResponseMapper {

    WeatherResponse.Read toReadResponse(WeatherModel.Read readModel);
}
