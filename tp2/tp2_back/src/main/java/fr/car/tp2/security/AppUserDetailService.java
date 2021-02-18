package fr.car.tp2.security;

import fr.car.tp2.auth.Auth;
import fr.car.tp2.auth.AuthNotFoundException;
import fr.car.tp2.auth.AuthService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AppUserDetailService implements UserDetailsService {

    private final AuthService authService;

    public AppUserDetailService(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Recover user by username
     * Attention: for our case, username = email
     * @param email
     * @return user details
     */
    @Override
    public final UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        final Auth auth;
        try {
            auth = this.authService.getAuthByEmail(email);
        } catch (AuthNotFoundException e) {
            throw new UsernameNotFoundException("Auth with email '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(auth.getPassword()).authorities(Collections.emptyList())
                .accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).build();
    }

}
