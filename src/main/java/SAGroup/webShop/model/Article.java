package SAGroup.webShop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double cost;

    private String description;


    public Article(String name, Double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public Article() {
    }
}
