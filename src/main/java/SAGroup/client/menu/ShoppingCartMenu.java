package SAGroup.client.menu;

import SAGroup.client.shoppingcart.ShoppingCartClient;
import java.io.IOException;
import java.util.Scanner;

public class ShoppingCartMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(String token) {
        ShoppingCartClient shoppingCartClient = new ShoppingCartClient("http://localhost:8080", token);

        while (true) {
            System.out.println("\nKundvagnsmeny:");
            System.out.println("1. Visa min kundvagn");
            System.out.println("2. Lägg till artikel i kundvagnen");
            System.out.println("3. Genomför utcheckning");
            System.out.println("4. Töm min kundvagn");
            System.out.println("5. Återgå till användarmenyn");
            System.out.print("Val: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showCart(shoppingCartClient);
                    break;
                case "2":
                    addArticleToCart(shoppingCartClient);
                    break;
                case "3":
                    checkout(shoppingCartClient);
                    break;
                case "4":
                    emptyCart(shoppingCartClient);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private static void showCart(ShoppingCartClient shoppingCartClient) {
        try {
            String cartContents = shoppingCartClient.getMyShoppingCart();
            System.out.println("Din kundvagn:\n" + cartContents);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte visa kundvagnen: " + e.getMessage());
        }
    }

    private static void addArticleToCart(ShoppingCartClient shoppingCartClient) {
        System.out.print("Ange artikel-ID: ");
        Long articleId = Long.parseLong(scanner.nextLine());
        System.out.print("Ange antal: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        try {
            String response = shoppingCartClient.addToMyShoppingCart(articleId, quantity);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte lägga till artikeln i kundvagnen: " + e.getMessage());
        }
    }

    private static void checkout(ShoppingCartClient shoppingCartClient) {
        try {
            String response = shoppingCartClient.checkoutMyCart();
            System.out.println("Utcheckning genomförd: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte genomföra utcheckningen: " + e.getMessage());
        }
    }

    private static void emptyCart(ShoppingCartClient shoppingCartClient) {
        try {
            String response = shoppingCartClient.emptyMyShoppingCart();
            System.out.println("Kundvagnen tömd: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte tömma kundvagnen: " + e.getMessage());
        }
    }
}