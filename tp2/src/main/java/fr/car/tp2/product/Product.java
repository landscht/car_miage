package fr.car.tp2.product;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Product {
    @Id
    private Long idProduct;
    private String image;
    private float price;
    private int stock;
    private String description;
}
