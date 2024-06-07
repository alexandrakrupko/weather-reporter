package weather.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import weather.model.Weather;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
}