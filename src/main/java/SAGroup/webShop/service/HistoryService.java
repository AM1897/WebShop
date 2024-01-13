package SAGroup.webShop.service;

import SAGroup.login.model.UserEntity;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.Article;
import SAGroup.webShop.model.ArticleDTO;
import SAGroup.webShop.model.CheckoutHistoryDTO;
import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.repository.CheckoutHistoryRepo;
import SAGroup.webShop.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    private final ShoppingCartRepo shoppingCartRepo;
    private final UserService userService;

    private final ArticleService articleService;
    private final CheckoutHistoryRepo checkoutHistoryRepo;

    @Autowired
    public HistoryService(ShoppingCartRepo shoppingCartRepo, UserService userService, ArticleService articleService, CheckoutHistoryRepo checkoutHistoryRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.userService = userService;
        this.articleService = articleService;
        this.checkoutHistoryRepo = checkoutHistoryRepo;
    }

    public List<String> storedUsersWithHistory() {

        List <String> allUsers = new ArrayList<>();
        try {
            // Assuming checkoutHistoryRepo is an instance of JpaRepository<CheckoutHistory, Long>
            checkoutHistoryRepo.findAll().forEach(article -> {
                String user = article.getUser();
                if (user != null) {
                    if(allUsers.contains(user) == false) {
                        System.out.println("Added user: " + user);
                        allUsers.add(user);
                    }
                }
            });
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log or rethrow
            e.printStackTrace(); // Replace with your exception handling strategy
        }

        return allUsers;
    }


    public List<CheckoutHistoryDTO> historyOfOneUser(String userName) {

        // Hämta userName
        List <CheckoutHistoryDTO> historyDTO = new ArrayList<>();
        checkoutHistoryRepo.findByUser(userName).forEach(article -> {

            ArticleDTO articleDTO = new ArticleDTO();
            // id, name, description, price,
            articleDTO.setId(article.getId());
            articleDTO.setName(article.getName());
            articleDTO.setDescription(article.getDescription());
            articleDTO.setPrice(article.getPrice());
            int amount = article.getAmount();

            CheckoutHistoryDTO checkoutHistoryDTO = new CheckoutHistoryDTO(userName,
                    articleDTO.getId(), articleDTO.getName(), articleDTO.getDescription(), articleDTO.getPrice()
                    , amount);

            historyDTO.add(checkoutHistoryDTO);

            System.out.println(articleDTO.toString() + " amout: " + amount + "\n");
        });
        return historyDTO;

    }
}
