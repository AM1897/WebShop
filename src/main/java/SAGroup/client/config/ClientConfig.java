package SAGroup.client.config;

public class ClientConfig {

    private final String apiBaseUrl;
    private final int connectionTimeout;
    private final int readTimeout;

    public ClientConfig(String apiBaseUrl, int connectionTimeout, int readTimeout) {
        this.apiBaseUrl = apiBaseUrl;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    @Override
    public String toString() {
        return "ClientConfig{" +
                "apiBaseUrl='" + apiBaseUrl + '\'' +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                '}';
    }
}
