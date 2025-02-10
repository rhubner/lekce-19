package cz.robotdreams.java.lekce18.weather;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class WeatherClient {

    private final String url;

    private final HttpClient httpClient;

    private final ObjectMapper jsonMapper;

    public WeatherClient(String url) {
        this.url = url;
        httpClient = HttpClient.newBuilder().build();
        jsonMapper = new ObjectMapper();
    }

    public WeatherResponse getWeather(String city) throws WeatherException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + "?q=" + city)) //V produkcnim kodu pouzivejte vzdy URL builder. Ja jsem nechtel pridavat dalsi zavislosti.
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")));

            if (response.statusCode() == 200) {
                return jsonMapper.readValue(response.body(), WeatherResponse.class);
            } else if (response.statusCode() == 404) {
                throw new WeatherException("City not found: " + city);
            }

        } catch (URISyntaxException e) {
            throw new WeatherException("invalid URL");
        } catch (IOException ioException) {
            throw new WeatherException(ioException);
        } catch (InterruptedException ex) {
            throw new WeatherException(ex);
        }
        return null;
    }

}