package SAGroup.webShop.repository;

import SAGroup.webShop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {


}
