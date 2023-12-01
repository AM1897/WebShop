package SAGroup.webShop.model;

import SAGroup.login.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "shoppingCarts")
@Data
public class ShoppingCart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(targetEntity = Article.class, cascade = CascadeType.ALL)
    private List<Article> articles;

}