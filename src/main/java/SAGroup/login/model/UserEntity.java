package SAGroup.login.model;

import SAGroup.webShop.model.ShoppingCart;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Getter
    @Setter
    private Roles role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
}
