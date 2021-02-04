package fr.car.tp2.user;

public class UserNotFoundException extends Exception{

    UserNotFoundException(Long id) {
        super("User " + id + " not found");
    }

}
