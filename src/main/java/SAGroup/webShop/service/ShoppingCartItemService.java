package SAGroup.webShop.service;

import SAGroup.webShop.model.ShoppingCartItem;
import SAGroup.webShop.repository.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return shoppingCartItemRepository.findAll();
    }

    public ShoppingCartItem getShoppingCartItemById(Long id) {
        return shoppingCartItemRepository.findById(id).orElse(null);
    }

    public ShoppingCartItem saveShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    public void deleteShoppingCartItem(Long id) {
        shoppingCartItemRepository.deleteById(id);
    }
}