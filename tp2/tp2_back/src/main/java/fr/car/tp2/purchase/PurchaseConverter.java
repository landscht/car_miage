package fr.car.tp2.purchase;

import fr.car.tp2.converter.GenericConverter;
import fr.car.tp2.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PurchaseConverter implements GenericConverter<PurchaseDto,Purchase> {

    private ProductService productService;

    @Override
    public PurchaseDto entityToDto(Purchase purchase) {
        return PurchaseDto.builder()
                .idProduct(purchase.getProduct().getId())
                .quantity(purchase.getQuantity())
                .build();
    }

    @Override
    public Purchase dtoToEntity(PurchaseDto purchaseDto) {
        return Purchase.builder()
                .product(productService.getProductById(purchaseDto.getIdProduct()))
                .quantity(purchaseDto.getQuantity())
                .build();
    }
}
