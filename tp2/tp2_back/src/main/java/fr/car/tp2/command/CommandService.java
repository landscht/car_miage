package fr.car.tp2.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;

    public Command saveCommand(Command command){
        return commandRepository.save(command);
    }
}
