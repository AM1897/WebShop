package SAGroup.webShop.controller;

import SAGroup.login.model.UserEntity;
import SAGroup.login.repo.UserRepo;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.model.ShoppingCartItem;
import SAGroup.webShop.service.ArticleService;
import SAGroup.webShop.service.ShoppingCartItemService;
import SAGroup.webShop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ArticleService articleService;

    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartController(UserService userService, ShoppingCartService shoppingCartService, ArticleService articleService, ShoppingCartItemService shoppingCartItemService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.articleService = articleService;

        this.shoppingCartItemService = shoppingCartItemService;
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<ShoppingCart> getShoppingCartByUserId(Principal principal) {

        UserEntity user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(user);

        if (shoppingCart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{itemId}")
    public ResponseEntity<?> addItemToCart(Principal principal, @PathVariable Long itemId) {

        UserEntity user = userService.findByUsername(principal.getName());
        Article article = articleService.finById(itemId);
        if (article == null) {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
        shoppingCartService.saveToShoppingCart(user, article);

        return new ResponseEntity<>("Article added to cart", HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{itemId}/{amount}")
    public ResponseEntity<?> updateCartItem(Principal principal, @PathVariable Long itemId, @PathVariable int amount) {

        UserEntity user = userService.findByUsername(principal.getName());

        if (shoppingCartService.updateCartItem(user, itemId, amount)) {
            return new ResponseEntity<>("Article updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article not found in cart", HttpStatus.NOT_FOUND);
        }


    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItemFromCart(Principal principal, @PathVariable Long itemId) {

        UserEntity user = userService.findByUsername(principal.getName());
        ShoppingCartItem item = shoppingCartItemService.getShoppingCartItemById(itemId);

        if (shoppingCartService.deleteItemFromShoppingCart(user, item)) {
            return new ResponseEntity<>("Article removed from cart", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article not found in cart", HttpStatus.NOT_FOUND);
        }
    }
}
