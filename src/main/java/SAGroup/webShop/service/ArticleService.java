package SAGroup.webShop.service;



import SAGroup.webShop.model.Article;
import SAGroup.webShop.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article finById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> finAll() {

        return articleRepository.findAll();
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
