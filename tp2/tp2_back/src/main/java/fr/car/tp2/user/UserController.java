package fr.car.tp2.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

}
