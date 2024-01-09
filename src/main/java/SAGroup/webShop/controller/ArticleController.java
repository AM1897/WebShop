package SAGroup.webShop.controller;

import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ArticleDTO;
import SAGroup.webShop.service.ArticleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @PostConstruct
    public void init() {

        if (articleService.getAllArticles().isEmpty()) {
        Article article1 = new Article();
        article1.setName("Book1");
        article1.setDescription("Author1");
        article1.setPrice("10");
        article1.setAmount(10);

        Article article2 = new Article();
        article2.setName("Book2");
        article2.setDescription("Author2");
        article2.setPrice("20");
        article2.setAmount(20);

        Article article3 = new Article();
        article3.setName("Book3");
        article3.setDescription("Author3");
        article3.setPrice("30");
        article3.setAmount(30);

            articleService.createArticle(article1);
            articleService.createArticle(article2);
            articleService.createArticle(article3);
        }

    }

    private final ArticleService articleService;


    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(article -> new ResponseEntity<>(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Article updated = articleService.updateArticle(id, updatedArticle);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
