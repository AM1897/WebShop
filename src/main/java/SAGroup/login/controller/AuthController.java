package SAGroup.login.controller;

import SAGroup.login.filter.PasswordValidator;
import SAGroup.login.model.AuthRequest;
import SAGroup.login.model.Roles;
import SAGroup.login.model.UserEntity;
import SAGroup.login.service.JWTService;
import SAGroup.login.service.UserService;
import SAGroup.webShop.controller.ShoppingCartController;
import SAGroup.webShop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final ShoppingCartService shoppingCartService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JWTService jwtService, ShoppingCartController shoppingCart, ShoppingCartService shoppingCartService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.shoppingCartService = shoppingCartService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest user) {

        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        } else {

            String badPass = PasswordValidator.validatePassword(user.getPassword());
            if (!badPass.equals("")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badPass);
            }
            UserEntity newUser = new UserEntity();
            newUser.setUsername(user.getUsername());
            newUser.setRole(Roles.ROLE_USER);
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            userService.save(newUser);

            shoppingCartService.createShoppingCart(newUser);

            return ResponseEntity.status(HttpStatus.OK).body("Registration successful!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>(jwtService.generateToken(authRequest.getUsername()), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
    }
}
