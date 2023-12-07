package SAGroup.webShop.model;

import SAGroup.login.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private UserEntity user;

    @OneToMany(targetEntity = Article.class, cascade = CascadeType.ALL)
    private List<Article> articles;

}