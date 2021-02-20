package fr.car.tp2.user;

import fr.car.tp2.command.Command;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String lastname;
    private String firstname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Command> commands;

}
