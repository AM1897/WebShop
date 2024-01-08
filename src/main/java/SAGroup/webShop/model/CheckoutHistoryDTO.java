package SAGroup.webShop.model;

import lombok.Data;

@Data
public class CheckoutHistoryDTO {

    private Long id;
    private String name;
    private String description;
    private String price;
    private int amount;

    private String user;

    public CheckoutHistoryDTO(String user, Long id, String name, String description, String price, int amount) {
        this.user = user;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }


}
