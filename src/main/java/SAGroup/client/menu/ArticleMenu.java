package SAGroup.client.menu;


import SAGroup.client.articleClient.ArticleClient;

import java.io.IOException;
import java.util.Scanner;


public class ArticleMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(String token) {
        while (true) {
            System.out.println("\nArticle Menu:");
            System.out.println("1. Skapa artikel");
            System.out.println("2. Visa alla artiklar");
            System.out.println("3. Hämta artikel med ID");
            System.out.println("4. Uppdatera artikel med ID");
            System.out.println("5. Ta bort artikel med ID");
            System.out.println("6. Tillbaka till adminmenyn");
            System.out.print("Val: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createArticle(token);
                    break;
                case 2:
                    viewAllArticles(token);
                    break;
                case 3:
                    getArticleById(token);
                    break;
                case 4:
                    updateArticleById(token);
                    break;
                case 5:
                    deleteArticleById(token);
                    break;
                case 6:
                    return; // Återgår till adminmenyn
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }

    private static void createArticle(String token) {
        System.out.println("Skapa en ny artikel.");

        System.out.println("Artikelns namn:");
        String name = scanner.nextLine();

        System.out.println("Beskrivning:");
        String description = scanner.nextLine();

        System.out.println("Pris:");
        String price = scanner.nextLine();

        System.out.println("Mängd:");
        int amount;
        try {
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mängd måste vara ett nummer.");
            return;
        }

        // Skapa en JSON-sträng med de insamlade uppgifterna
        String articleDetailsJson = String.format(
                "{\"name\": \"%s\", \"description\": \"%s\", \"price\": \"%s\", \"amount\": %d}",
                name, description, price, amount
        );

        // Anropa ArticleClient för att skapa artikeln
        ArticleClient articleClient = new ArticleClient("http://localhost:8080", token);
        try {
            String response = articleClient.createArticle(articleDetailsJson);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte skapa artikeln: " + e.getMessage());
        }
    }


    private static void viewAllArticles(String token) {
        ArticleClient articleClient = new ArticleClient("http://localhost:8080", token);
        try {
            String articles = articleClient.getAllArticles();
            System.out.println("Alla artiklar: \n" + articles);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta artiklarna: " + e.getMessage());
        }
    }

    private static void getArticleById(String token) {
        System.out.println("Ange artikel-ID:");
        Long id = Long.parseLong(scanner.nextLine());

        ArticleClient articleClient = new ArticleClient("http://localhost:8080", token);
        try {
            String article = articleClient.getArticleById(id);
            System.out.println("Artikeldetaljer: \n" + article);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte hämta artikeln: " + e.getMessage());
        }
    }

    private static void updateArticleById(String token) {
        System.out.println("Uppdatera en artikel.");

        System.out.println("Ange ID för artikeln som ska uppdateras:");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID måste vara ett nummer.");
            return;
        }

        System.out.println("Artikelns nya namn (lämna blankt om inga ändringar):");
        String name = scanner.nextLine();

        System.out.println("Ny beskrivning (lämna blankt om inga ändringar):");
        String description = scanner.nextLine();

        System.out.println("Nytt pris (lämna blankt om inga ändringar):");
        String price = scanner.nextLine();

        System.out.println("Ny mängd (lämna blankt om inga ändringar):");
        String amount = scanner.nextLine();  // Ändrade från int till String för att tillåta blank inmatning

        String articleDetailsJson = String.format(
                "{%s%s%s%s}",
                name.isEmpty() ? "" : String.format("\"name\": \"%s\"", name),
                description.isEmpty() ? "" : String.format(", \"description\": \"%s\"", description),
                price.isEmpty() ? "" : String.format(", \"price\": \"%s\"", price),
                amount.isEmpty() ? "" : String.format(", \"amount\": %s", amount)  // Antal utan citattecken eftersom det är en integer
        );

        // Anropa ArticleClient för att uppdatera artikeln
        ArticleClient articleClient = new ArticleClient("http://localhost:8080", token);
        try {
            String response = articleClient.updateArticle(id, articleDetailsJson);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte uppdatera artikeln: " + e.getMessage());
        }
    }


    private static void deleteArticleById(String token) {
        System.out.println("Ange ID för den artikel som ska tas bort:");
        Long id = Long.parseLong(scanner.nextLine());

        ArticleClient articleClient = new ArticleClient("http://localhost:8080", token);
        try {
            String response = articleClient.deleteArticle(id);
            System.out.println("Svar från servern: " + response);
        } catch (IOException | InterruptedException e) {
            System.out.println("Kunde inte ta bort artikeln: " + e.getMessage());
        }
    }
}
