package weather.exception;

import lombok.Getter;

@Getter
public class WeatherNotFoundException extends RuntimeException {

    private final String city;

    public WeatherNotFoundException(String message, String city) {
        super(message);
        this.city = city;
    }
}