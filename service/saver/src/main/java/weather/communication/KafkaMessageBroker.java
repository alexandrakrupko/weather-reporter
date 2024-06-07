package weather.communication;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import weather.api.dto.WeatherDto;
import weather.mapper.WeatherMapper;
import weather.service.WeatherService;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageBroker implements MessageBroker {

    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    @KafkaListener(topics = "weather", groupId = "weather", containerFactory = "kafkaJsonListenerContainerFactory")
    public void consumeWeather(@NonNull WeatherDto weatherDto) {
        log.info("Saver received weather: '{}'", weatherDto);
        weatherService.save(weatherMapper.mapWeatherDtoInWeather(weatherDto));
    }
}
