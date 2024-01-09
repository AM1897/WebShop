package SAGroup.webShop.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Checkout_History")
@Data
@NoArgsConstructor
public class CheckoutHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    private int amount;

    private String user;

    public CheckoutHistory(String user, Long id, String name, String description, String price, int amount) {
        this.user = user;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

}













