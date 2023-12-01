package SAGroup.webShop.repository;


import SAGroup.webShop.model.ShoppingCart;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {



    @Query("SELECT s FROM ShoppingCart s WHERE s.owner = ?1")
    Optional<ShoppingCart> findByOwner(String owner);




}
