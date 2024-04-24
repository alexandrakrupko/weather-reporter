package weather.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import weather.model.Weather;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Query(nativeQuery = true,
            value = """
                    SELECT * FROM Weather
                    WHERE city LIKE :city
                    AND timestamp >= current_date AND timestamp < current_date + 1
                    ORDER BY timestamp DESC
                    LIMIT 1
                    """)
    Optional<Weather> findByCityAndCurrentDate(@NonNull String city);
}
