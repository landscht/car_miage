package main.java.user;

import java.util.Map;

public class UserConstant {

    public static final String username = "admin";
    public static final String password = "admin";

    public boolean goodUsername(String username) {
        return username.equals(this.username);
    }

    public boolean goodPassword(String password) {
        return password.equals(this.password);
    }


}
