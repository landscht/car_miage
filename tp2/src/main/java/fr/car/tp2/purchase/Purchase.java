package fr.car.tp2.purchase;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Product product;

}
