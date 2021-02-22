package fr.car.tp2.user;

import fr.car.tp2.auth.AuthNotFoundException;
import fr.car.tp2.auth.AuthService;
import fr.car.tp2.security.jwt.JWTFilter;
import fr.car.tp2.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @CrossOrigin
    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @CrossOrigin
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) throws AuthNotFoundException {
        String email = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        return ResponseEntity.ok(authService.getAuthByEmail(email).getUser());
    }

}
