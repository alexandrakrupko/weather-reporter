package weather.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import weather.model.Weather;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Query("""
            SELECT w FROM Weather w
            WHERE w.city ILIKE :city
            AND w.timestamp >= current_date
            ORDER BY timestamp DESC
            LIMIT 1
            """)
    Optional<Weather> findByCityAndCurrentDate(@NonNull String city);
}
