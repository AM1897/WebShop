package SAGroup.client.shoppingcart;

import SAGroup.client.articleClient.ArticleDTO;

import java.util.List;

public class ShoppingCartDTO {

    private Long userId;
    private Long cartId;
    private List<ArticleDTO> articles;

    // Standardkonstruktorer, getters och setters

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long userId, Long cartId, List<ArticleDTO> articles) {
        this.userId = userId;
        this.cartId = cartId;
        this.articles = articles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    // En toString()-metod kan vara användbar för att skriva ut objektinformation
    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "userId=" + userId +
                ", cartId=" + cartId +
                ", articles=" + articles +
                '}';
    }
}
