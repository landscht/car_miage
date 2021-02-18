package fr.car.tp2.security.jwt;

import fr.car.tp2.security.AppConfig;
import fr.car.tp2.security.AppUserDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    private final String secretKey;

    private final long tokenValidityInMilliseconds;

    private final AppUserDetailService userService;

    public TokenProvider(AppConfig config, AppUserDetailService userService) {
        this.secretKey = Base64.getEncoder().encodeToString(config.getSecret().getBytes());
        this.tokenValidityInMilliseconds = 1000 * config.getTokenValidityInSeconds();
        this.userService = userService;
    }

    /**
     * Create new JWT token
     * @param email
     * @return token
     */
    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(email)
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).compact();
    }

    /**
     * Recover user information by JWT token
     * @param token
     * @return user information
     */
    public Authentication getAuthentication(String token) {
        String username = this.getUsername(token);
        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Recover user email by JWT token
     * @param token
     * @return user email
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token)
                .getBody().getSubject();
    }
}
