package SAGroup.webShop.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @Column(nullable = false)
    private Integer quantity;

    // Standard getters och setters
    // ...
}

