package weather.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

import static org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager weatherCacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .withCacheConfiguration("weatherCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5)))
                .build();
    }
}
