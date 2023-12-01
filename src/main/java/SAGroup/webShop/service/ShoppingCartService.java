package SAGroup.webShop.service;


import SAGroup.login.model.UserEntity;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.model.ShoppingCartItem;
import SAGroup.webShop.repository.ShoppingCartItemRepository;
import SAGroup.webShop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ArticleService articleService;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    public ShoppingCartService(ArticleService articleService, UserService userService, ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCardItemService) {
        this.articleService = articleService;
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCardItemService;
    }

    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }
    public ShoppingCart getShoppingCartByUser(UserEntity newUser) {

        ShoppingCart cart = shoppingCartRepository.findByOwner(newUser.getUsername()).orElse(null);
        if (cart == null) {
            return null;
        } else {
            return  cart;
        }

    }

    public void saveToShoppingCart(UserEntity user, Article article) {

        Article article1 = articleService.finById(article.getId());
        ShoppingCart shoppingCart = shoppingCartRepository.findByOwner(user.getUsername()).orElse(null);


        if (article1 != null && shoppingCart != null) {
            ShoppingCartItem item = new ShoppingCartItem();
            item.setArticle(article1);
            item.setShoppingCart(shoppingCart);
            item.setQuantity(1);

            shoppingCartItemRepository.save(item);



        }
    }

    public Boolean deleteItemFromShoppingCart(UserEntity user, ShoppingCartItem item) {

        ShoppingCart shoppingCart = getShoppingCartByUser(user);
        List<ShoppingCartItem> items = shoppingCart.getItems();
        if (items.contains(item)) {

            items.remove(item);
            shoppingCartRepository.save(shoppingCart);
            return true;
        }else {
            return false;
        }

    }
    public boolean updateCartItem(UserEntity user, Long itemId, int amount) {

        ShoppingCart shoppingCart = getShoppingCartByUser(user);
        List<ShoppingCartItem> items = shoppingCart.getItems();

        if (items.contains(itemId)) {
            int index = items.indexOf(itemId);
            ShoppingCartItem itemInCart = items.get(index);
            itemInCart.setQuantity(amount);
            shoppingCartItemRepository.save(itemInCart);
            return true;
        }else {
            return false;
        }
    }

}

