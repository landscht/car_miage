package fr.car.tp2.user;

import fr.car.tp2.command.Command;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String lastname;
    private String firstname;
    private String email;
    private String password;

    @OneToMany
    private List<Command> commands;

}
