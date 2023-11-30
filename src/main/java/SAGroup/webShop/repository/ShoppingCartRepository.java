package SAGroup.webShop.repository;


import SAGroup.webShop.model.ShoppingCart;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    boolean existsByName(String name);
}
