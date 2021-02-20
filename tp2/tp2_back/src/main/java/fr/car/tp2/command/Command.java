package fr.car.tp2.command;

import fr.car.tp2.purchase.Purchase;
import fr.car.tp2.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Command {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Purchase> purchases;

}
