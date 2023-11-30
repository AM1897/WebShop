package SAGroup.webShop.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private Integer quantity;


}

