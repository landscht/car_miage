package fr.car.tp2.command;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;

    @PostMapping
    public Command saveCommand(@RequestBody Command command){
        return commandService.saveCommand(command);
    }

}
