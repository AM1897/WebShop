package SAGroup.webShop.controller;

import SAGroup.login.model.UserEntity;
import SAGroup.login.repo.UserRepo;
import SAGroup.webShop.model.CheckoutHistoryDTO;
import SAGroup.webShop.service.HistoryService;
import SAGroup.webShop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final ShoppingCartService shoppingCartService;

    private final HistoryService historyService;
    private final UserRepo userRepo;

    @Autowired
    public HistoryController(ShoppingCartService shoppingCartService, HistoryService historyService ,UserRepo userRepo) {
        this.shoppingCartService = shoppingCartService;
        this.historyService = historyService;
        this.userRepo = userRepo;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<String>> checkoutHistoryOfUsers(Principal principal) {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Printing all users that have a checkout history with their user ID");
        return ResponseEntity.ok(historyService.storedUsersWithHistory());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userName}")
    public ResponseEntity<List<CheckoutHistoryDTO>> checkoutHistoryOfAnUser(@PathVariable String userName) {

        System.out.println("Printing the user : " + userName + " with their checkout history");
        return ResponseEntity.ok(historyService.historyOfOneUser(userName));
    }

}
