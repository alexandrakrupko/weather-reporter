package weather.util;

public class OpenWeatherClientTestUtils {

    public static String createUri(String city, String apiKey, String units) {
        return String.join("",
                "https://api.openweathermap.org/data/2.5/weather?q=", city,
                "&appid=", apiKey,
                "&units=", units);
    }
}
