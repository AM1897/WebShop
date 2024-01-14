package SAGroup.Client;

import java.util.Scanner;

public class AdminMenu {
    private ApiClient apiClient;
    private Scanner scanner;

    public AdminMenu(ApiClient apiClient, Scanner scanner) {
        this.apiClient = apiClient;
        this.scanner = scanner;
    }

    public boolean visaAdminMenu() {
        while (true) {
            System.out.println("\nAdminmeny:");
            System.out.println("1. Visa alla användare");
            System.out.println("2. Uppdatera användare");
            System.out.println("3. Ta bort användare");
            System.out.println("4. Hantera artiklar");
            System.out.println("5. Se alla användares kundvagnhistorik");
            System.out.println("6. Logga ut");
            System.out.println("7. Tillbaka till huvudmenyn");
            System.out.print("Val: ");
            String adminChoice = scanner.nextLine();

            switch (adminChoice) {
                case "1":
                    showAllUsers();
                    break;
                case "2":
                    updateUser();
                    break;
                case "3":
                    deleteUser();
                    break;
                case "4":
                    ArticleMenu articleMenu = new ArticleMenu(apiClient, scanner);
                    articleMenu.visaArticleMenu();
                    break;
                case "5":
                    showAllUsersHistory();
                    break;
                case "6":
                    return false;
                case "7":
                    return true;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
        }
    }

    private void showAllUsers() {
        try {
            String users = apiClient.getAllUsers();
            System.out.println("Användare: " + users);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta användare. Kontrollera din anslutning och försök igen.");
            e.printStackTrace();
        }
    }

    private void updateUser() {
        System.out.print("Ange användarens ID som ska uppdateras: ");
        Long userId = Long.parseLong(scanner.nextLine());
        System.out.print("Ange nytt användarnamn (eller tryck Enter för att hoppa över): ");
        String username = scanner.nextLine();
        System.out.print("Ange nytt lösenord (eller tryck Enter för att hoppa över): ");
        String password = scanner.nextLine();

        try {
            String result = apiClient.updateUser(userId, username, password);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Fel vid uppdatering av användare: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteUser() {
        System.out.print("Ange användarens ID som ska tas bort: ");
        Long userId = Long.parseLong(scanner.nextLine());
        try {
            String result = apiClient.deleteUser(userId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Fel vid borttagning av användare: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showAllUsersHistory() {
        try {
            String history = apiClient.getAllUsersHistory();
            System.out.println("Alla användares kundvagnshistorik:");
            System.out.println(history);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta kundvagnshistoriken. Kontrollera din anslutning och försök igen.");
            e.printStackTrace();
        }
    }

}
