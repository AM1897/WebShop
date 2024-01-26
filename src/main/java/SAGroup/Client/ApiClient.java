package SAGroup.Client;

import SAGroup.login.model.ChangingPassword;
import SAGroup.login.model.UserEntity;
import SAGroup.webShop.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;


public class ApiClient {
    private HttpClient httpClient;
    private String baseUrl;
    private String jwtToken;

    public ApiClient(String baseUrl) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = baseUrl;
    }

    public boolean login(String username, String password) throws IOException, InterruptedException {
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/login"))
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .POST(BodyPublishers.ofString("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                .build();

        HttpResponse<String> response = httpClient.send(loginRequest, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            this.jwtToken = parseJwtToken(response.body());
            return true;
        }
        return false;
    }

    public String getAllArticles() throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/articles"))
                .GET();

        if (jwtToken != null && !jwtToken.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + jwtToken);
        }

        HttpResponse<String> response = httpClient.send(requestBuilder.build(), BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }


    private String parseJwtToken(String responseString) {
        return responseString;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    public String register(String username, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Registrering lyckades!";
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode() + " " + response.body());
        }
    }

    public String deleteUser(Long userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/" + userId))
                .header("Authorization", "Bearer " + jwtToken)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "User deleted successfully";
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode() + " " + response.body());
        }
    }

    public String updateUser(Long userId, String username, String password) throws IOException, InterruptedException {
        UserEntity updatedUser = new UserEntity();
        if (username != null && !username.isEmpty()) {
            updatedUser.setUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            updatedUser.setPassword(password); // Antag att lösenordet krypteras på serversidan
        }

        String userJson = new ObjectMapper().writeValueAsString(updatedUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/" + userId))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .PUT(BodyPublishers.ofString(userJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "User updated successfully";
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode() + " " + response.body());
        }
    }


    public String getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/all"))
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Metod för att hämta användarinformation
    public String getUserInfo(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/" + username))
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Metod för att ändra användarens lösenord
    public String changeUserPassword(String oldPassword, String newPassword, String confirmPassword) throws IOException, InterruptedException {
        ChangingPassword changingPassword = new ChangingPassword();
        changingPassword.setOldPassword(oldPassword);
        changingPassword.setNewPassword(newPassword);
        changingPassword.setConfirmPassword(confirmPassword);

        String json = new ObjectMapper().writeValueAsString(changingPassword);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/changePass"))
                .header("Authorization", "Bearer " + jwtToken)
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Lösenordet har ändrats.";
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }


    // Metod för att ta bort det nuvarande användarkontot
    public String deleteCurrentUserAccount() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/users/me"))
                .header("Authorization", "Bearer " + jwtToken)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Kontot har tagits bort.";
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    // Metod för att hämta den aktuella användarens kundvagn
    public String getShoppingCart() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/shopping-carts"))
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }

    public String addArticleToShoppingCart(Long articleId, int quantity) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/shopping-carts/" + articleId + "/" + quantity))
                .header("Authorization", "Bearer " + jwtToken)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Article added to the shopping cart successfully";
        } else {
            throw new IOException("Failed to add article to shopping cart: HTTP error code : " + response.statusCode());
        }
    }

    public String removeArticleFromShoppingCart(Long articleId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/shopping-carts/remove-article/" + articleId))
                .header("Authorization", "Bearer " + jwtToken)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Article removed from the shopping cart successfully";
        } else {
            throw new IOException("Failed to remove article from shopping cart: HTTP error code : " + response.statusCode());
        }
    }

    public String checkout() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/shopping-carts/checkout"))
                .header("Authorization", "Bearer " + jwtToken)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Checkout successful";
        } else {
            throw new IOException("Checkout failed: HTTP error code : " + response.statusCode());
        }
    }


    public String createArticle(Article article) {
        try {
            String json = new ObjectMapper().writeValueAsString(article);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/articles"))
                    .header("Authorization", "Bearer " + jwtToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                return "Article created successfully";
            } else {
                return "Failed to create article: HTTP error code : " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public String getArticleById(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/articles/" + id))
                    .header("Authorization", "Bearer " + jwtToken)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Failed to get article: HTTP error code : " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public String updateArticleById(Long id, Article updatedArticle) {
        try {
            String json = new ObjectMapper().writeValueAsString(updatedArticle);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/articles/" + id))
                    .header("Authorization", "Bearer " + jwtToken)
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return "Article updated successfully";
            } else {
                return "Failed to update article: HTTP error code : " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public String deleteArticleById(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/articles/" + id))
                    .header("Authorization", "Bearer " + jwtToken)
                    .DELETE()
                    .build();

            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            if (response.statusCode() == 204) {
                return "Article deleted successfully";
            } else {
                return "Failed to delete article: HTTP error code : " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }



    public String getAllUsersHistory() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/history/all"))
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }


    public String getUserHistory(String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/history/" + userName))
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed : HTTP error code : " + response.statusCode());
        }
    }
    public String getCurrentUserHistory(String jwtToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/history/current")) // Ersätt "/history/current" med din faktiska endpoint
                .header("Authorization", "Bearer " + jwtToken)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed to get user history: HTTP error code : " + response.statusCode());
        }
    }

}
