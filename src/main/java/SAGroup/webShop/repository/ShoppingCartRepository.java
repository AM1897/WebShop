package SAGroup.webShop.repository;


import SAGroup.webShop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    // Här kan du lägga till anpassade frågor, t.ex. för att hitta kundkorg baserat på användar-ID
    // Optional<ShoppingCart> findByUserId(Long userId);
}
