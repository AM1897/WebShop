package SAGroup.client.auth;

public class TokenManager {

    private String token;

    public TokenManager() {
        this.token = null;
    }

    public void saveToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public boolean hasValidToken() {
        // Här kan du implementera logik för att kontrollera token giltighet,
        // som att kontrollera dess utgångstidpunkt om din token innehåller sådan information.
        // För enkelhetens skull antar vi här att token alltid är giltig om den inte är null.
        return token != null && !token.isEmpty();
    }

    public void clearToken() {
        this.token = null;
    }
}
