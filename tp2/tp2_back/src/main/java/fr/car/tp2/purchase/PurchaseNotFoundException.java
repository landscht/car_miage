package fr.car.tp2.purchase;

public class PurchaseNotFoundException extends Exception {

    PurchaseNotFoundException(Long id) {
        super("Purchase " + id + " not found");
    }

}
