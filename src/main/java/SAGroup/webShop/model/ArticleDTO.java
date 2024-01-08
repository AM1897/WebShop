package SAGroup.webShop.model;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private String name;
    private String description;
    private String price;
}
