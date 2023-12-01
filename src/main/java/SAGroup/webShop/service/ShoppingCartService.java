package SAGroup.webShop.service;

import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepo.findAll();
    }

    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartRepo.findById(id);
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    public ShoppingCart updateShoppingCart(Long id, ShoppingCart updatedShoppingCart) {
        if (shoppingCartRepo.existsById(id)) {
            updatedShoppingCart.setId(id);
            return shoppingCartRepo.save(updatedShoppingCart);
        } else {
            // Handle not found exception or return null, depending on your use case
            return null;
        }
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepo.deleteById(id);
    }
}
