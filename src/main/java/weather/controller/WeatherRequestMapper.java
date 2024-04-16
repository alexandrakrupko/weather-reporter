package weather.controller;

import org.mapstruct.Mapper;
import weather.service.WeatherModel;

@Mapper(componentModel = "spring")
public interface WeatherRequestMapper {

    WeatherModel.Create toCreateModel(WeatherRequest.Create createRequest);
}
