package SAGroup.webShop.model;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {

    private Long userId;
    private Long cartId;
    private List<Article> articles;

}
