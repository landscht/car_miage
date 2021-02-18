package fr.car.tp2.auth;

public class AuthNotFoundException extends Exception {

    AuthNotFoundException(String email) {
        super("Auth " + email + " not found");
    }

}
