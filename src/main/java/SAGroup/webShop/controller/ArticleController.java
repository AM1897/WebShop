package SAGroup.webShop.controller;

import SAGroup.webShop.model.ArticleWithNoId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> getAllArticles() {
        // Här ska logik för att hämta alla artiklar implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {
        // Här ska logik för att hämta en specifik artikel implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> createArticle(@RequestBody ArticleWithNoId article) {
        // Här ska logik för att skapa en ny artikel implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleWithNoId article) {
        // Här ska logik för att uppdatera en artikel implementeras
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        // Här ska logik för att radera en artikel implementeras
        return ResponseEntity.ok().build();
    }
}

