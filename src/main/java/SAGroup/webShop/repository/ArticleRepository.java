package SAGroup.webShop.repository;

import SAGroup.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Här kan du lägga till anpassade frågor, t.ex. för att hitta artiklar efter namn
    // List<Article> findByNameContaining(String name);
}

