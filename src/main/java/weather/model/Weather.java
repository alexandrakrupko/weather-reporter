package weather.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather-seq")
    @SequenceGenerator(name = "weather-seq", sequenceName = "weather_id_seq", allocationSize = 10)
    private Long id;
    private @NonNull String city;
    private @NonNull LocalDateTime timestamp;
    @Embedded
    private @NonNull Temperature temperature;
}
