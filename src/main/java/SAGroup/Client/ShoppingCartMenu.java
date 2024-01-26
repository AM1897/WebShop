package SAGroup.Client;

import java.io.IOException;
import java.util.Scanner;

public class ShoppingCartMenu {
    private ApiClient apiClient;
    private Scanner scanner;

    public ShoppingCartMenu(ApiClient apiClient, Scanner scanner) {
        this.apiClient = apiClient;
        this.scanner = scanner;
    }

    public void visaShoppingCartMenu() {
        while (true) {
            System.out.println("\nKundvagnsmeny:");
            System.out.println("1. Visa min kundvagn");
            System.out.println("2. Lägg till artikel");
            System.out.println("3. Ta bort artikel");
            System.out.println("4. Gå till kassan");
            System.out.println("5. Tillbaka till huvudmenyn");
            System.out.print("Val: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewShoppingCart();
                    break;
                case "2":
                    addArticleToCart();
                    break;
                case "3":
                    removeArticleFromCart();
                    break;
                case "4":
                    checkout();
                    break;
                case "5":
                    return; // Återgår till huvudmenyn
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
        }
    }

    private void viewShoppingCart() {
        try {
            String cartContents = apiClient.getShoppingCart();
            System.out.println("Din kundvagn:");
            System.out.println(cartContents);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ett fel inträffade när kundvagnen skulle visas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addArticleToCart() {
        System.out.print("Ange artikelns ID som du vill lägga till i kundvagnen: ");
        Long articleId = Long.parseLong(scanner.nextLine());
        System.out.print("Ange kvantiteten: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        try {
            String result = apiClient.addArticleToShoppingCart(articleId, quantity);
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ett fel inträffade när artikeln skulle läggas till: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void removeArticleFromCart() {
        System.out.print("Ange artikelns ID som du vill ta bort från kundvagnen: ");
        Long articleId = Long.parseLong(scanner.nextLine());
        try {
            String result = apiClient.removeArticleFromShoppingCart(articleId);
            System.out.println(result);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ett fel inträffade när artikeln skulle tas bort: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void checkout() {
        try {
            String checkoutResult = apiClient.checkout();
            System.out.println(checkoutResult);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ett fel inträffade vid utcheckningen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
