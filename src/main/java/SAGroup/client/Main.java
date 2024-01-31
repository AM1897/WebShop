package SAGroup.client;


import SAGroup.client.articleClient.ArticleClient;
import SAGroup.client.auth.AuthClient;
import SAGroup.client.menu.AdminMenu;
import SAGroup.client.menu.UserMenu;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static AuthClient authClient;

    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080";
        authClient = new AuthClient(baseUrl);

        while (true) {
            System.out.println("\nVälkommen till applikationen. Välj ett alternativ:");
            System.out.println("1. Logga in som Admin");
            System.out.println("2. Logga in som Användare");
            System.out.println("3. Registrera");
            System.out.println("4. Visa alla artiklar");
            System.out.println("5. Avsluta");
            System.out.print("Val: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    registerUser();
                    break;
                case 4:
                    viewArticles();
                    break;
                case 5:
                    System.out.println("Avslutar programmet...");
                    System.exit(0);
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Admin Login...");
        System.out.print("Ange användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();

        try {
            String response = authClient.login(username, password);
            System.out.println("Inloggning lyckades: " + response);
            AdminMenu.showMenu( response);
        } catch (Exception e) {
            System.out.println("Inloggning misslyckades: " + e.getMessage());
        }
    }



    private static void userLogin() {
        System.out.println("User Login...");
        System.out.print("Ange användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();

        try {
            String token = authClient.login(username, password);
            System.out.println("Inloggning lyckades: " + token);
            UserMenu.showMenu(token); // Skickar token till UserMenu
        } catch (Exception e) {
            System.out.println("Inloggning misslyckades: " + e.getMessage());
        }
    }



    private static void registerUser() {
        System.out.println("Registrera ny användare...");
        System.out.print("Ange användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();

        try {
            String response = authClient.register(username, password);
            System.out.println("Registrering lyckades: " + response);
        } catch (Exception e) {
            System.out.println("Registrering misslyckades: " + e.getMessage());
        }
    }

    private static void viewArticles() {
        System.out.println("Visar alla artiklar...");
        try {
            ArticleClient articleClient = new ArticleClient("http://localhost:8080", null);
            String articles = articleClient.getAllArticles();
            System.out.println(articles);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta artiklar: " + e.getMessage());
        }
    }
}
