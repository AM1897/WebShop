package SAGroup.client.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;

public class HttpUtil {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<String> sendGetRequest(String url, String authToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + authToken)
                .GET()
                .build();

        return httpClient.send(request, BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendPostRequest(String url, String body, String authToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .POST(BodyPublishers.ofString(body))
                .build();

        return httpClient.send(request, BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendPutRequest(String url, String body, String authToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .PUT(BodyPublishers.ofString(body))
                .build();

        return httpClient.send(request, BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendDeleteRequest(String url, String authToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + authToken)
                .DELETE()
                .build();

        return httpClient.send(request, BodyHandlers.ofString());
    }
}

