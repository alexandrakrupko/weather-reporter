package weather.util;

public class StringUtils {

    public static String capitalizeFirstLetter(String initial) {
        return initial.substring(0, 1).toUpperCase() + initial.substring(1);
    }
}
