package fr.car.tp2.purchase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public Purchase savePurchase(Purchase purchase) {
        return this.purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(Long id) throws PurchaseNotFoundException {
        return this.purchaseRepository.findById(id).orElseThrow(() -> new PurchaseNotFoundException(id));
    }

}
