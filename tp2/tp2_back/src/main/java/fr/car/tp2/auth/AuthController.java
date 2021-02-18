package fr.car.tp2.auth;

import fr.car.tp2.security.jwt.JWTFilter;
import fr.car.tp2.security.jwt.TokenProvider;
import fr.car.tp2.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthController(PasswordEncoder passwordEncoder, AuthService authService, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Check if user is authenticate
     */
    @GetMapping("/authenticate")
    public void authenticate() {
        // Empty
    }

    /**
     * Authenticate a user
     * @param signin
     * @return token
     */
    @PostMapping(value = "/signin", consumes = { "application/json" })
    public ResponseEntity<String> signin(@RequestBody Auth signin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signin.getEmail(), signin.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
            return new ResponseEntity<>(tokenProvider.createToken(signin.getEmail()), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Recover a user authentificated
     * @return user
     */
    @GetMapping(value = "/me")
    public ResponseEntity<User> recoverUserAuthentificated(HttpServletRequest request) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        Auth auth = authService.getAuthByEmail(requestEmail);
        return new ResponseEntity<>(auth.getUser(), HttpStatus.OK);
    }

    /**
     * Register a new user
     * @param auth
     * @return token
     */
    @PostMapping(value = "/signup", consumes = { "application/json" })
    public ResponseEntity<String> signup(@RequestBody Auth auth) {
        if (authService.emailExists(auth.getEmail())) {
            return new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        }

        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authService.saveAuth(auth);
        return new ResponseEntity<>(tokenProvider.createToken(auth.getEmail()), HttpStatus.CREATED);
    }

}
