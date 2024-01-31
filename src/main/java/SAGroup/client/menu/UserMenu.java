package SAGroup.client.menu;

import SAGroup.client.user.UserClient;

import java.io.IOException;
import java.util.Scanner;

public class UserMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(String token) {
        UserClient userClient = new UserClient("http://localhost:8080", token);

        while (true) {
            System.out.println("\nAnvändarmeny:");
            System.out.println("1. Visa min info");
            System.out.println("2. Ändra mitt lösenord");
            System.out.println("3. Ta bort mitt konto");
            System.out.println("4. Hantera min kundvagn");
            System.out.println("5. Logga ut och återgå till huvudmenyn");
            System.out.print("Val: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showMyInfo(token);
                    break;
                case "2":
                    // Ändra lösenord
                    changePassword(userClient, token);
                    break;
                case "3":
                    // Ta bort konto
                    deleteAccount(userClient, token);
                    break;
                case "4":
                    // Hantera kundvagn
                    manageCart(userClient, token);
                    break;
                case "5":
                    // Logga ut och återgå till huvudmenyn
                    return;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private static void showMyInfo(String token) {
        UserClient userClient = new UserClient("http://localhost:8080", token);
        try {
            String userInfo = userClient.getMyUserInfo(token);
            System.out.println("Min användarinformation:\n" + userInfo);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta användarinformation: " + e.getMessage());
        }
    }


    private static void changePassword(UserClient userClient, String token) {
        System.out.println("Ange ditt gamla lösenord:");
        String oldPassword = scanner.nextLine();
        System.out.println("Ange ditt nya lösenord:");
        String newPassword = scanner.nextLine();
        System.out.println("Bekräfta ditt nya lösenord:");
        String confirmPassword = scanner.nextLine();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("De nya lösenorden matchar inte.");
            return;
        }

        // Skapa JSON-strängen för lösenordsändring
        String passwordChangeJson = String.format("{\"oldPassword\": \"%s\", \"newPassword\": \"%s\", \"confirmPassword\": \"%s\"}", oldPassword, newPassword, confirmPassword);

        try {
            String response = userClient.changePassword(passwordChangeJson);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte ändra lösenord: " + e.getMessage());
        }
    }

    private static void deleteAccount(UserClient userClient, String token) {
        System.out.println("Är du säker på att du vill ta bort ditt konto? Ange 'ja' för att bekräfta:");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("ja")) {
            System.out.println("Kontoborttagning avbruten.");
            return;
        }

        try {
            String response = userClient.deleteMyAccount();
            System.out.println("Svar från servern: " + response);
            // Användaren har tagit bort sitt konto, så logga ut dem
            return;
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte ta bort konto: " + e.getMessage());
        }
    }

    private static void manageCart(UserClient userClient, String token) {
        // Implementera logik för att hantera kundvagn
    }
}
