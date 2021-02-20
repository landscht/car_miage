package fr.car.tp2.purchase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDto {
    private Long idProduct;
    private int quantity;
}
