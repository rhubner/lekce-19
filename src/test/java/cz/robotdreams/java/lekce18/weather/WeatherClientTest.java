package cz.robotdreams.java.lekce18.weather;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;


public class WeatherClientTest {

    private WireMockServer wireMockServer;

    private int port = 8080;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", port);
    }

    @AfterEach
    void teardown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    private void stubPrague() {

        stubFor(get(urlPathTemplate("/weather"))
                .withQueryParam("q", matching("Prague"))
                .willReturn(aResponse().withStatus(200)
                        .withBodyFile("prague.json")));

    }

    @Test
    public void testClient() {
        stubPrague();

        WeatherClient weatherClient = new WeatherClient("http://localhost:8080/weather");

        WeatherResponse response = weatherClient.getWeather("Prague");

        assertThat(response).isNotNull();
        assertThat(response.getWeather()).isEqualTo("Sunny sky");
        assertThat(response.getWind()).isNotNull();
        assertThat(response.getWind().getSpeed()).isEqualTo(1.53, withPrecision(0.03d));

    }




}