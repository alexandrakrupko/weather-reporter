package weather.communication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "weather-topic", groupId = "weather")
    public void listen(String message) {
        log.info("Saver received message: '{}'", message);
    }
}
