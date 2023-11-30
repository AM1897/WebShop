package SAGroup.webShop.controller;

import SAGroup.model.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("/")
    public ResponseEntity<?> getAllArticles() {
        // Här ska logik för att hämta alla artiklar implementeras
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable Long articleId) {
        // Här ska logik för att hämta en specifik artikel implementeras
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        // Här ska logik för att skapa en ny artikel implementeras
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<?> updateArticle(@PathVariable Long articleId, @RequestBody Article article) {
        // Här ska logik för att uppdatera en artikel implementeras
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId) {
        // Här ska logik för att radera en artikel implementeras
        return ResponseEntity.ok().build();
    }
}

