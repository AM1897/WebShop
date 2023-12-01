package SAGroup.webShop.controller;

import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ArticleWithNoId;
import SAGroup.webShop.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Article>> getAllArticles() {
        // Create and save articles
        if (articleService.finAll().isEmpty()) {
            Article phone = new Article("Phone", 499.99, "Smartphone");
            Article laptop = new Article("Laptop", 1299.99, "High-performance laptop");
            Article book = new Article("Book", 19.99, "Bestseller");
            Article coffeeMaker = new Article("Coffee Maker", 79.99, "Automatic coffee machine");
            Article headphones = new Article("Headphones", 89.99, "Wireless headphones");

            articleService.save(phone);
            articleService.save(laptop);
            articleService.save(book);
            articleService.save(coffeeMaker);
            articleService.save(headphones);
        }


        List<Article> articles = articleService.finAll();

        // Return the list of articles in the ResponseEntity
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {

        Article article = articleService.finById(id);

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleWithNoId article) {

        articleService.save(new Article(article.getName(), article.getCost(), article.getDescription()));


        return new ResponseEntity<>("Article created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleWithNoId article) {


        Article article1 = articleService.finById(id);
        if (article1 != null) {
            article1.setName(article.getName());
            article1.setCost(article.getCost());
            article1.setDescription(article.getDescription());

            articleService.save(article1);
            return new ResponseEntity<>("Article updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {

        articleService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

