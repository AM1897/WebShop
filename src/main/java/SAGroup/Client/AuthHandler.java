package SAGroup.Client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthHandler {

    private String baseUrl;
    private String jwtToken;

    public AuthHandler(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean login(String username, String password) {
        try {
            HttpPost loginRequest = new HttpPost(baseUrl + "/auth/login");
            String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
            StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
            loginRequest.setEntity(entity);
            loginRequest.setHeader("Accept", "application/json");
            loginRequest.setHeader("Content-type", "application/json");

            HttpResponse response = HttpClients.createDefault().execute(loginRequest);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                // Använd ett bibliotek som Jackson eller Gson för att extrahera JWT-token från JSON-svaret
                this.jwtToken = extractTokenFromResponse(responseString);
                return true;
            } else {
                // Hantera olika typer av fel här
                System.err.println("Login failed with status code: " + statusCode);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String extractTokenFromResponse(String response) {

        return response;
    }

/*    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }*/

}
