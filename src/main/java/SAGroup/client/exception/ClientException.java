package SAGroup.client.exception;

public class ClientException extends Exception {

    private final String detailedMessage;

    public ClientException(String message) {
        super(message);
        this.detailedMessage = message;
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        this.detailedMessage = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "detailedMessage='" + detailedMessage + '\'' +
                '}';
    }
}

