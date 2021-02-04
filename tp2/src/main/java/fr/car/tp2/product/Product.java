package fr.car.tp2.product;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String image;
    private float price;
    private int stock;
    private String description;
}
