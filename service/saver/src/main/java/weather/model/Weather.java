package weather.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document("weathers")
public class Weather {

    @Id
    private String id;
    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    private @NonNull Temperature temperature;
}