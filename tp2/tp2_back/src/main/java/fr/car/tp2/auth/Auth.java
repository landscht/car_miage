package fr.car.tp2.auth;

import fr.car.tp2.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Auth {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
