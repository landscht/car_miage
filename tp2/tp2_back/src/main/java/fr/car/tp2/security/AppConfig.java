package fr.car.tp2.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe de configuration de l'application
 */
@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppConfig {
    private String secret;

    private long tokenValidityInSeconds;
}