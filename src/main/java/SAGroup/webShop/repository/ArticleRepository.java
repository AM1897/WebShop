package SAGroup.webShop.repository;


import SAGroup.webShop.model.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    boolean existsByName(String name);
}

