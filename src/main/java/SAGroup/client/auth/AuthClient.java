package SAGroup.client.auth;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AuthClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String login(String username, String password) throws IOException, InterruptedException {
        String url = baseUrl + "/auth/login";
        String json = objectMapper.writeValueAsString(Map.of("username", username, "password", password));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        handleResponse(response);
        return response.body();
    }

    public String register(String username, String password) throws IOException, InterruptedException {
        String url = baseUrl + "/auth/register";
        String json = objectMapper.writeValueAsString(Map.of("username", username, "password", password));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        handleResponse(response);
        return response.body();
    }

    private void handleResponse(HttpResponse<String> response) {
        if (response.statusCode() >= 400) {
            System.out.println("Error: " + response.statusCode() + " " + response.body());
            // Här kan du lägga till ytterligare logik beroende på statuskoden
        }
    }
}
