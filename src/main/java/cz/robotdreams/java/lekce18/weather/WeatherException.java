package cz.robotdreams.java.lekce18.weather;

public class WeatherException extends RuntimeException {

    public WeatherException(String message) {
        super(message);
    }

    public WeatherException(Throwable cause) {
        super(cause);
    }
}
