package SAGroup.webShop.model;


import jakarta.persistence.*;
import lombok.Data;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

@Entity
@Table(name = "articles")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    private int amount;


}
