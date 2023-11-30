package SAGroup.webShop.model;

import SAGroup.login.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ShoppingCartItem> items;

}

