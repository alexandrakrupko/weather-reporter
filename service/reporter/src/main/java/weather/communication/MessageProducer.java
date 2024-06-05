package weather.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        log.info("Sending message '{}' to topic '{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        sendMessage("weather-topic", "Hello World ----" + new Random().nextInt());
    }
}
