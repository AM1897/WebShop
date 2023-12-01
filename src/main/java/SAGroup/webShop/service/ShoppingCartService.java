package SAGroup.webShop.service;

import SAGroup.login.model.UserEntity;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ShoppingCart;
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

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, UserService userService) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.userService = userService;
    }

    public ShoppingCart createShoppingCart(UserEntity user) {
        UserEntity user1 = userService.findByUsername(user.getUsername());

        if (user1 == null) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user1);

        List<Article> articles = new ArrayList<>();
        shoppingCart.setArticles(articles);

        // Save the user with the associated shopping cart
        user1.setShoppingCart(shoppingCart);
        userService.save(user1);

        return shoppingCartRepo.save(shoppingCart);
    }

    public Optional<ShoppingCart> getShoppingCartByUserId(Long userId) {

        ShoppingCart shoppingCart = shoppingCartRepo.findByUser_Id(userId).orElse(null);
        if (shoppingCart != null) {
            return shoppingCartRepo.findByUser_Id(userId);
        }
        return null;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepo.findAll();
    }

    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartRepo.findById(id);
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
