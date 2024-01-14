package SAGroup.Client;

import java.io.IOException;
import java.util.Scanner;

public class HistoryMenu {
    private ApiClient apiClient;
    private Scanner scanner;
    private String jwtToken;

    public HistoryMenu(ApiClient apiClient, Scanner scanner, String jwtToken) {
        this.apiClient = apiClient;
        this.scanner = scanner;
        this.jwtToken = jwtToken;
    }

    public void visaHistoryMenu(boolean isAdmin) {
        while (true) {
            System.out.println("\nHistorik Meny:");
            if (isAdmin) {
                System.out.println("1. Visa alla användares historik");
                System.out.println("2. Visa specifik användares historik");
            } else {
                System.out.println("1. Visa min historik");
            }
            System.out.println("3. Tillbaka till föregående meny");
            System.out.print("Val: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (isAdmin) {
                        showAllUsersHistory();
                    } else {
                        showCurrentUserHistory();
                    }
                    break;
                case "2":
                    if (isAdmin) {
                        showSpecificUserHistory();
                    }
                    break;
                case "3":
                    return; // Återgår till föregående meny
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
        }
    }

    private void showAllUsersHistory() {
        try {
            String allUsersHistory = apiClient.getAllUsersHistory();
            System.out.println("Historik för alla användare:");
            System.out.println(allUsersHistory);
        } catch(Exception e) {
            System.out.println("Ett fel inträffade vid hämtning av historik för alla användare: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showSpecificUserHistory() {
        System.out.print("Ange användarnamnet för den användare vars historik du vill se: ");
        String userName = scanner.nextLine();

        try {
            String userHistory = apiClient.getUserHistory(userName);
            System.out.println("Historik för användaren " + userName + ":");
            System.out.println(userHistory);
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid hämtning av historik för användaren " + userName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showCurrentUserHistory() {
        try {
            // Antag att getCurrentUserHistory() tar jwtToken som parameter och hämtar historiken för den aktuella användaren
            String currentUserHistory = apiClient.getCurrentUserHistory(jwtToken);
            System.out.println("Din köphistorik:");
            System.out.println(currentUserHistory);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ett fel inträffade vid hämtning av din historik: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
