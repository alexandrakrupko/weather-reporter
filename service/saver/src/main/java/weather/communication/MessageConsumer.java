package weather.communication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "weather", groupId = "weather")
    public void listen(String weatherMessage) {
        log.info("Saver received dto: '{}'", weatherMessage);
    }
}
