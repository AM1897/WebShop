package SAGroup.login.controller;

import SAGroup.login.filter.PasswordValidator;
import SAGroup.login.model.AuthRequest;
import SAGroup.login.model.Roles;
import SAGroup.login.model.UserEntity;
import SAGroup.login.service.JWTService;
import SAGroup.login.service.UserService;
import SAGroup.webShop.model.ShoppingCart;
import SAGroup.webShop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// UserController is a REST controller that handles user-related requests.
// It is annotated with @RestController to indicate that it's a controller where every method returns a domain object instead of a view.
// It's also annotated with @RequestMapping("/users") to map web requests onto specific handler classes and/or handler methods.
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final ShoppingCartService shoppingCartService;

    // Constructor for UserController, it takes an AuthenticationManager, UserService, PasswordEncoder, and JWTService as parameters.
    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JWTService jwtService, ShoppingCartService shoppingCartService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.shoppingCartService = shoppingCartService;
    }

    //----------------------------------------------------------------------
    // Login and register Methods here...
    //----------------------------------------------------------------------
    // The register method handles the registration of new users.
    // It checks if the username already exists, if not, it creates a new user with the provided details.
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

            return ResponseEntity.status(HttpStatus.OK).body("Registration successful!");
        }
    }

    // The authAndGetToken method handles the authentication of users.
    // It authenticates the user and if successful, generates a JWT token for the user.
    // Here you can ask for payment or something else before generating the token.
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
