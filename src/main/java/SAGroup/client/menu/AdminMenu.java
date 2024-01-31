package SAGroup.client.menu;

import SAGroup.client.history.HistoryClient;
import SAGroup.client.user.UserClient;
import java.util.Scanner;
import java.io.IOException;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(String token) {
        while (true) {
            System.out.println("\nAdminmeny:");
            System.out.println("1. Visa alla användare");
            System.out.println("2. Uppdatera användare");
            System.out.println("3. Ta bort användare");
            System.out.println("4. Hantera artiklar");
            System.out.println("5. Se alla användares kundvagnshistorik");
            System.out.println("6. Logga ut");
            System.out.print("Val: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllUsers(token);
                    break;
                case 2:
                    updateUser(token);
                    break;
                case 3:
                    deleteUser(token);
                    break;
                case 4:
                    ArticleMenu.showMenu(token); // Se till att denna metod finns i ArticleMenu-klassen
                    break;
                case 5:
                    showShoppingCartHistory(token);
                    break;
                case 6:
                    System.out.println("Du har loggat ut.");
                    return;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }
    private static void showAllUsers(String token) {
        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String users = userClient.getAllUsers();
            System.out.println("Alla användare: \n" + users);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta användarlistan: " + e.getMessage());
        }
    }

    private static void updateUser(String token) {
        System.out.println("Ange ID för användaren som ska uppdateras:");
        Long userId = Long.parseLong(scanner.nextLine());

        System.out.println("Ange det nya användarnamnet:");
        String newUsername = scanner.nextLine();

        System.out.println("Ange det nya lösenordet:");
        String newPassword = scanner.nextLine();

        // Skapa JSON-strängen
        String userDetailsJson = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", newUsername, newPassword);

        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String response = userClient.updateUser(userId, userDetailsJson);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte uppdatera användaren: " + e.getMessage());
        }
    }

    private static void deleteUser(String token) {
        System.out.println("Ange ID för användaren som ska tas bort:");
        Long userId;
        try {
            userId = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt ID. Ange ett numeriskt värde.");
            return;
        }

        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String response = userClient.deleteUser(userId);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte ta bort användaren: " + e.getMessage());
        }
    }
    private static void showShoppingCartHistory(String token) {
        System.out.println("Hämtar kundvagnshistorik för alla användare...");

        // Skapa instansen av en client som kan hantera historik, ex. HistoryClient
        HistoryClient historyClient = new HistoryClient("http://localhost:8080", token);

        try {
            String history = historyClient.getAllUsersShoppingCartHistory();
            System.out.println("Alla användares kundvagnshistorik:\n" + history);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta kundvagnshistoriken: " + e.getMessage());
        }
    }
}



























































/*
import SAGroup.client.user.UserClient;

import java.io.IOException;
import java.util.Scanner;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(String token) {
        while (true) {
            System.out.println("\nAdminmeny:");
            System.out.println("1. Visa alla användare");
            System.out.println("2. Uppdatera användare");
            System.out.println("3. Ta bort användare");
            System.out.println("4. Hantera artiklar");
            System.out.println("5. Se alla användares kundvagnshistorik");
            System.out.println("6. Logga ut");
            System.out.println("7. Tillbaka till huvudmenyn");
            System.out.print("Val: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllUsers(token);
                    break;
                case 2:
                    updateUser(token);
                    break;
                case 3:
                    // Ta bort användare
                    break;
                case 4:
                    // Hantera artiklar
                    break;
                case 5:
                    // Se alla användares kundvagnshistorik
                    break;
                case 6:
                    // Logga ut
                    return;
                case 7:
                    // Gå tillbaka till huvudmenyn
                    return;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }        private static void showAllUsers(String token) {
        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String users = userClient.getAllUsers();
            System.out.println("Alla användare: \n" + users);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta användarlistan: " + e.getMessage());
        }
    }
    private static void updateUser(String token) {
        System.out.println("Ange ID för användaren som ska uppdateras:");
        Long userId = Long.parseLong(scanner.nextLine());

        System.out.println("Ange det nya användarnamnet:");
        String newUsername = scanner.nextLine();

        System.out.println("Ange det nya lösenordet:");
        String newPassword = scanner.nextLine();

        // Skapa JSON-strängen
        String userDetailsJson = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", newUsername, newPassword);

        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String response = userClient.updateUser(userId, userDetailsJson);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte uppdatera användaren: " + e.getMessage());
        }
    }

}
*/