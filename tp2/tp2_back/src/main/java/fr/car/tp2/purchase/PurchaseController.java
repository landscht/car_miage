package fr.car.tp2.purchase;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseConverter purchaseConverter;

    @CrossOrigin
    @PostMapping
    public Purchase save(@RequestBody PurchaseDto purchaseDto){
        return purchaseService.savePurchase(purchaseConverter.dtoToEntity(purchaseDto));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) throws PurchaseNotFoundException {
        return purchaseService.getPurchaseById(id);
    }


}
