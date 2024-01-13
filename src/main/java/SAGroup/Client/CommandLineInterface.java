package SAGroup.Client;

import java.util.Scanner;

public class CommandLineInterface {

    private ApiClient apiClient;
    private Scanner scanner;

    public CommandLineInterface(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    // Hantera logga in
                    handleLogin();
                    break;
                case "2":
                    // Hantera visa artiklar
                    handleDisplayArticles();
                    break;
                case "3":
                    // Hantera andra funktioner...
                    break;
                case "0":
                    running = false;
                    System.out.println("Stänger programmet...");
                    break;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Huvudmeny ---");
        System.out.println("1. Logga in");
        System.out.println("2. Visa artiklar");
        System.out.println("3. Andra funktioner...");
        System.out.println("0. Avsluta");
        System.out.print("Ange ditt val: ");
    }

    private void handleLogin() {
        System.out.print("Ange användarnamn: ");
        String username = scanner.nextLine();
        System.out.print("Ange lösenord: ");
        String password = scanner.nextLine();
        // Anropa en metod i ApiClient för att logga in
        // Exempel: apiClient.login(username, password);
    }

    private void handleDisplayArticles() {
        // Anropa en metod i ApiClient för att visa artiklar
        // Exempel: apiClient.getAllArticles();
    }

    // Andra privata metoder för att hantera specifika uppgifter...

    // Slutligen, inte glömma att stänga Scanner när programmet avslutas
    public void close() {
        scanner.close();
    }
}
