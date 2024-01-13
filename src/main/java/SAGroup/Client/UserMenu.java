package SAGroup.Client;

import java.util.Scanner;

public class UserMenu {
    private ApiClient apiClient;
    private Scanner scanner;
    private String jwtToken;
    private String currentUsername;

    public UserMenu(ApiClient apiClient, Scanner scanner, String jwtToken, String currentUsername) {
        this.apiClient = apiClient;
        this.scanner = scanner;
        this.jwtToken = jwtToken;
        this.currentUsername = currentUsername;
    }

    public boolean visaUserMenu() {
        boolean continueInMainMenu = true;
        while (true) {
            System.out.println("\nAnvändarmeny:");
            System.out.println("1. Visa min info");
            System.out.println("2. Ändra mitt lösenord");
            System.out.println("3. Ta bort mitt konto");
            System.out.println("4. Hantera min kundvagn");
            System.out.println("5. Logga ut och återgå till huvudmenyn");
            System.out.print("Val: ");
            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    showUserInfo();
                    break;
                case "2":
                    changePassword();
                    break;
                case "3":
                    if (deleteAccount()) {
                        continueInMainMenu = false;
                        break;
                    }
                    break;
                case "4":
                    ShoppingCartMenu shoppingCartMenu = new ShoppingCartMenu(apiClient, scanner, jwtToken);
                    shoppingCartMenu.visaShoppingCartMenu();
                    break;
                case "5":
                    continueInMainMenu = false;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
            if (!continueInMainMenu) {
                break;
            }
        }
        return continueInMainMenu;
    }

    private void showUserInfo() {
        try {
            String userInfo = apiClient.getUserInfo(currentUsername);
            System.out.println("Användarinfo: " + userInfo);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta användarinformation. Kontrollera din anslutning och försök igen.");
            e.printStackTrace();
        }
    }

    private void changePassword() {
        System.out.print("Ange ditt gamla lösenord: ");
        String oldPassword = scanner.nextLine();
        System.out.print("Ange ditt nya lösenord: ");
        String newPassword = scanner.nextLine();
        System.out.print("Bekräfta ditt nya lösenord: ");
        String confirmPassword = scanner.nextLine();

        try {
            String result = apiClient.changeUserPassword(oldPassword, newPassword, confirmPassword);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Fel vid ändring av lösenord: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean deleteAccount() {
        System.out.print("Är du säker på att du vill ta bort ditt konto? Skriv 'JA' för att bekräfta: ");
        String confirmation = scanner.nextLine();
        if ("JA".equalsIgnoreCase(confirmation)) {
            try {
                String result = apiClient.deleteCurrentUserAccount();
                System.out.println(result);
                return true;
            } catch (Exception e) {
                System.out.println("Fel vid borttagning av konto: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Borttagning av konto avbruten.");
            return false;
        }
    }
}
