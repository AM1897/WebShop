package SAGroup.webShop.service;


import SAGroup.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Metoder för att hantera artikelrelaterade operationer
    // public Article createArticle(Article article) { ... }
    // public Article getArticleById(Long id) { ... }
    // ...
}
