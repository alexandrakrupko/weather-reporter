package weather.communication;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import weather.api.dto.WeatherDto;

@Component
@RequiredArgsConstructor
@Slf4j
class KafkaMessageBroker implements MessageBroker {

    private final KafkaTemplate<String, WeatherDto> kafkaTemplate;

    @Override
    public void sendWeather(@NonNull WeatherDto weatherDto) {
        log.info("Sending WeatherDto '{}' to topic 'weather'", weatherDto);
        kafkaTemplate.send("weather", weatherDto);
    }
}