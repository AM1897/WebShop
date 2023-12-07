package SAGroup.webShop.service;

import SAGroup.login.model.UserEntity;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.model.ShoppingCartDTO;
import SAGroup.webShop.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;
    private final UserService userService;

    private final ArticleService articleService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, UserService userService, ArticleService articleService) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.userService = userService;
        this.articleService = articleService;
    }

    public ShoppingCart createShoppingCart(UserEntity user) {
        // Hitta användaren baserat på användarnamnet
        UserEntity userEntity = userService.findByUsername(user.getUsername());

        // Kontrollera om användaren existerar
        if (userEntity == null) {
            // Om användaren inte finns, hantera det på lämpligt sätt (kasta ett undantag, returnera null, etc.)
            return null;
        }

        // Kontrollera om användaren redan har en kundkorg
        if (userEntity.getShoppingCart() != null) {
            // Användaren har redan en kundkorg, så vi behöver inte skapa en ny
            // Här kan du antingen returnera den befintliga kundkorgen eller hantera det på något annat sätt
            return userEntity.getShoppingCart();
        }

        // Skapa en ny kundkorg om användaren inte har en
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userEntity);
        shoppingCart.setArticles(new ArrayList<>()); // Initiera med en tom artikel-lista

        // Spara den nya kundkorgen i databasen
        return shoppingCartRepo.save(shoppingCart);
    }

    public Optional<ShoppingCart> getShoppingCartByUserId(Long userId) {
        // Hämta kundkorgen baserat på användar-ID
        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findByUser_Id(userId);
        // Returnera resultatet direkt
        // Optional-handlingen är redan null-säker
        return shoppingCart;
    }

    public List<ShoppingCartDTO> getUserShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepo.findAll();
        List<ShoppingCartDTO> userShoppingCarts = new ArrayList<>();

        for (ShoppingCart cart : shoppingCarts) {
            UserEntity user = cart.getUser(); // Assuming there is a user associated with the shopping cart
            if (user != null) { // kontrollerar att varje kundkorg har en användare
                ShoppingCartDTO userShoppingCartDTO = new ShoppingCartDTO();
                userShoppingCartDTO.setUserId(user.getId());
                userShoppingCartDTO.setCartId(cart.getId());
                userShoppingCartDTO.setArticles(cart.getArticles()); // Assuming articles is a collection in the ShoppingCart entity

                userShoppingCarts.add(userShoppingCartDTO);
            }

        }

        return userShoppingCarts;
    }

    public double calculateTotalPrice(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepo.findByUser_Id(userId).orElse(null);
        if (shoppingCart == null) {
            return 0;
        }

        double totalPrice = 0;
        for (Article article : shoppingCart.getArticles()) {
            totalPrice += Double.parseDouble(article.getPrice()) * article.getAmount();
        }

        return totalPrice;
    }

    public void addArticleToShoppingCart(Long userId, Long articleId, int quantity) {
        UserEntity user = userService.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        Article article = articleService.findById(articleId);
        ShoppingCart shoppingCart = user.getShoppingCart();

        Optional<Article> existingArticle = shoppingCart.getArticles().stream()
                .filter(a -> a.getId().equals(articleId))
                .findFirst();

        if (existingArticle.isPresent()) {
            existingArticle.get().setAmount(existingArticle.get().getAmount() + quantity);
        } else {
            article.setAmount(quantity);
            shoppingCart.getArticles().add(article);
        }

        shoppingCartRepo.save(shoppingCart);
    }

    public ShoppingCart updateShoppingCart(Long id, ShoppingCart updatedShoppingCart) {
        if (shoppingCartRepo.existsById(id)) {
            updatedShoppingCart.setId(id);
            return shoppingCartRepo.save(updatedShoppingCart);
        } else {
            return null;
        }
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepo.deleteById(id);
    }
}
