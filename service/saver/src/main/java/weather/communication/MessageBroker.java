package weather.communication;

import lombok.NonNull;
import weather.api.dto.WeatherDto;

public interface MessageBroker {

    void consumeWeather(@NonNull WeatherDto weatherDto);
}
