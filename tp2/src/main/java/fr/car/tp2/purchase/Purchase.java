package fr.car.tp2.purchase;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

}
