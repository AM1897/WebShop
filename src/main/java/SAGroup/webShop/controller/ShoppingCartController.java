package SAGroup.webShop.controller;

import SAGroup.webShop.model.ShoppingCartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<?> getShoppingCartByUserId(Principal principal) {
        // Här ska logik för att hämta en användares kundkorg implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public ResponseEntity<?> addItemToCart(Principal principal, @RequestBody Long articleId) {
        // Här ska logik för att lägga till en artikel i kundkorgen implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{updateamount}")
    public ResponseEntity<?> updateCartItem(Principal principal, @RequestBody Long articleId) {
        // Här ska logik för att uppdatera en artikel i kundkorgen implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItemFromCart(Principal principal, @PathVariable Long itemId) {
        // Här ska logik för att ta bort en artikel från kundkorgen implementeras
        return ResponseEntity.ok().build();
    }
}
