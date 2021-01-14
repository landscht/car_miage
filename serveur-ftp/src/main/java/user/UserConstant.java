package main.java.user;

import java.util.Map;

public class UserConstant {

    private final String username = "admin";
    private final String password = "admin";

    public boolean goodUsername(String username) {
        return username.equals(this.username);
    }

    public boolean goodPassword(String password) {
        return password.equals(this.password);
    }


}
