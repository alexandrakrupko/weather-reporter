package weather.configuration.kafka;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@ConfigurationProperties("spring.kafka")
@Validated
@Getter
@Setter
class KafkaProperties {

    @NotNull(message = "spring.kafka.bootstrap-servers property is not specified")
    private List<String> bootstrapServers;
}
