package SAGroup.webShop.service;

import SAGroup.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    // Metoder för att hantera kundkorgsrelaterade operationer
    // public ShoppingCart addArticleToCart(Long userId, ShoppingCartItem item) { ... }
    // public ShoppingCart removeArticleFromCart(Long userId, Long itemId) { ... }
    // ...
}

