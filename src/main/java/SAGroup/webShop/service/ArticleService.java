package SAGroup.webShop.service;

import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ArticleDTO;
import SAGroup.webShop.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepo.findAll();
        List<ArticleDTO> dtos = new ArrayList<>();
        for (Article article : articles) {
            dtos.add(convertToDTO(article));
        }
        return dtos;
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    public Article createArticle(Article article) {
        return articleRepo.save(article);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        if (articleRepo.existsById(id)) {
            updatedArticle.setId(id);
            return articleRepo.save(updatedArticle);
        } else {
            // Handle not found exception or return null, depending on your use case
            return null;
        }
    }

    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }

    public Article findById(Long articleId) {
        return articleRepo.findById(articleId).orElse(null);
    }


    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setName(article.getName());
        dto.setDescription(article.getDescription());
        dto.setPrice(article.getPrice());
        return dto;
    }
}
