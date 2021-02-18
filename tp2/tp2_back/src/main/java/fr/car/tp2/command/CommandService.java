package fr.car.tp2.command;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;

    public Command saveCommand(Command command){
        return commandRepository.save(command);
    }

    public List<Command> getAllCommandByPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return commandRepository.findAll(pageable);
    }
}
