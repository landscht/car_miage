package fr.car.tp2.command;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;

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

}
