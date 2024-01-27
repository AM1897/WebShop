package SAGroup.Client;

import java.util.Scanner;

public class ApplicationRunner {

    private static AuthHandler authHandler;
    private static ApiClient apiClient;
    private static final String BASE_URL = "http://localhost:8080";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        authHandler = new AuthHandler(BASE_URL);
        apiClient = new ApiClient(BASE_URL);

        while (true) {
            System.out.println("Välkommen till applikationen. Välj ett alternativ:");
            System.out.println("1. Logga in som Admin");
            System.out.println("2. Logga in som Användare");
            System.out.println("3. Registrera");
            System.out.println("4. Visa alla artiklar");
            System.out.println("5. Avsluta");
            System.out.print("Val: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLogin(true);
                    break;
                case "2":
                    handleLogin(false);
                    break;
                case "3":
                    handleRegister();
                    break;
                case "4":
                    displayAllArticles();
                    break;
                case "5":
                    System.out.println("Stänger programmet...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
        }
    }

    private static void handleLogin(boolean isAdmin) {
        System.out.print("Användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Lösenord: ");
        String password = scanner.nextLine();

        try {
            boolean loggedIn = authHandler.login(username, password);
            if (loggedIn) {
                System.out.print("Ange din JWT-token: ");
                String jwtToken = scanner.nextLine();
                apiClient.setJwtToken(jwtToken);
                if (isAdmin) {
                    AdminMenu adminMenu = new AdminMenu(apiClient, scanner);
                    adminMenu.visaAdminMenu();
                } else {
                    // Här skapas en instans av UserMenu med alla fyra parametrar
                    UserMenu userMenu = new UserMenu(apiClient, scanner, jwtToken, username);
                    userMenu.visaUserMenu();
                }
            } else {
                System.out.println("Inloggning misslyckades, försök igen.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void handleRegister() {
        System.out.print("Välj ett användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Välj ett lösenord: ");
        String password = scanner.nextLine();

        try {
            String registerResult = apiClient.register(username, password);
            System.out.println(registerResult);
        } catch (Exception e) {
            System.out.println("Registrering misslyckades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayAllArticles() {
        try {
            String articles = apiClient.getAllArticles();
            System.out.println("Artiklar: " + articles);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta artiklar. Kontrollera din anslutning och försök igen.");
            e.printStackTrace();
        }
    }
}
