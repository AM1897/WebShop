package SAGroup.webShop.service;


import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}

