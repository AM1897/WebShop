package SAGroup.webShop.controller;

import SAGroup.model.ShoppingCartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @GetMapping("/{userId}")
    public ResponseEntity<?> getShoppingCartByUserId(@PathVariable Long userId) {
        // Här ska logik för att hämta en användares kundkorg implementeras
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> addItemToCart(@PathVariable Long userId, @RequestBody ShoppingCartItem item) {
        // Här ska logik för att lägga till en artikel i kundkorgen implementeras
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateCartItem(@PathVariable Long userId, @RequestBody ShoppingCartItem item) {
        // Här ska logik för att uppdatera en artikel i kundkorgen implementeras
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        // Här ska logik för att ta bort en artikel från kundkorgen implementeras
        return ResponseEntity.ok().build();
    }
}
