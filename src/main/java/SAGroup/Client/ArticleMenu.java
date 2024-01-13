package SAGroup.Client;

import SAGroup.webShop.model.Article;
import java.util.Scanner;

public class ArticleMenu {
    private ApiClient apiClient;
    private Scanner scanner;

    public ArticleMenu(ApiClient apiClient, Scanner scanner) {
        this.apiClient = apiClient;
        this.scanner = scanner;
    }

    public void visaArticleMenu() {
        while (true) {
            System.out.println("\nArticle Menu:");
            System.out.println("1. Skapa artikel");
            System.out.println("2. Visa alla artiklar");
            System.out.println("3. Hämta artikel med ID");
            System.out.println("4. Uppdatera artikel med ID");
            System.out.println("5. Ta bort artikel med ID");
            System.out.println("6. Tillbaka till adminmenyn");
            System.out.print("Val: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createArticle();
                    break;
                case "2":
                    getAllArticles();
                    break;
                case "3":
                    getArticleById();
                    break;
                case "4":
                    updateArticleById();
                    break;
                case "5":
                    deleteArticleById();
                    break;
                case "6":
                    return; // Återgår till adminmenyn
                default:
                    System.out.println("Ogiltigt val, försök igen.");
                    break;
            }
        }
    }

    private void createArticle() {
        System.out.print("Ange artikelns namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange artikelns beskrivning: ");
        String description = scanner.nextLine();
        System.out.print("Ange artikelns pris: ");
        String price = scanner.nextLine();
        System.out.print("Ange artikelns mängd: ");
        int amount = Integer.parseInt(scanner.nextLine());

        Article article = new Article();
        article.setName(name);
        article.setDescription(description);
        article.setPrice(price);
        article.setAmount(amount);

        String response = apiClient.createArticle(article);
        System.out.println(response);
    }

    private void getAllArticles() {
        try {
            String articles = apiClient.getAllArticles();
            System.out.println("Alla artiklar:");
            System.out.println(articles);
        } catch (Exception e) {
            System.out.println("Kunde inte hämta artiklar. Kontrollera din anslutning och försök igen.");
            e.printStackTrace();
        }
    }

    private void getArticleById() {
        System.out.print("Ange artikelns ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        String response = apiClient.getArticleById(id);
        System.out.println("Artikelinformation:");
        System.out.println(response);
    }

    private void updateArticleById() {
        System.out.print("Ange artikelns ID som ska uppdateras: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Ange nytt namn för artikeln (eller tryck Enter för att behålla nuvarande): ");
        String newName = scanner.nextLine();
        System.out.print("Ange ny beskrivning för artikeln (eller tryck Enter för att behålla nuvarande): ");
        String newDescription = scanner.nextLine();
        System.out.print("Ange nytt pris för artikeln (eller tryck Enter för att behålla nuvarande): ");
        String newPrice = scanner.nextLine();
        System.out.print("Ange ny mängd för artikeln (eller tryck Enter för att behålla nuvarande): ");
        String newAmount = scanner.nextLine();

        Article updatedArticle = new Article();
        if (!newName.isEmpty()) updatedArticle.setName(newName);
        if (!newDescription.isEmpty()) updatedArticle.setDescription(newDescription);
        if (!newPrice.isEmpty()) updatedArticle.setPrice(newPrice);
        if (!newAmount.isEmpty()) updatedArticle.setAmount(Integer.parseInt(newAmount));

        String response = apiClient.updateArticleById(id, updatedArticle);
        System.out.println(response);
    }

    private void deleteArticleById() {
        System.out.print("Ange artikelns ID som ska tas bort: ");
        Long id = Long.parseLong(scanner.nextLine());

        String response = apiClient.deleteArticleById(id);
        System.out.println(response);
    }
}