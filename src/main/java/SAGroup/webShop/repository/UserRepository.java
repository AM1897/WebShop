package SAGroup.webShop.repository;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SecurityProperties.User, Long> {

    // Här kan du lägga till anpassade metoder, t.ex. för att hitta användare efter användarnamn
    // Optional<User> findByUsername(String username);
}

