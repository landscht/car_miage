package fr.car.tp2.command;

import fr.car.tp2.auth.AuthNotFoundException;
import fr.car.tp2.auth.AuthService;
import fr.car.tp2.security.jwt.JWTFilter;
import fr.car.tp2.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;
    private final TokenProvider tokenProvider;
    private final AuthService authService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Command> saveCommand(@RequestBody Command command){
        return ResponseEntity.ok(commandService.saveCommand(command));
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Command>> getAllCommand(@RequestParam int page) {
        return ResponseEntity.ok(commandService.getAllCommandByPage(page));
    }

    @CrossOrigin
    @GetMapping("/user")
    public ResponseEntity<List<Command>> getAllCommandByUser(HttpServletRequest request) throws AuthNotFoundException {
        Long id = authService.getAuthByEmail(tokenProvider.getUsername(JWTFilter.resolveToken(request))).getUser().getId();
        return ResponseEntity.ok(commandService.getAllCommandByUser(id));
    }

}
