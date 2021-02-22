package fr.car.tp2.command;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.ZoneOffset;
=======
>>>>>>> b592c1d24daa561cfb0aa08b319c0af98bb97d51
import java.util.List;

@Service
@AllArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;

    public Command saveCommand(Command command){
        command.setDate(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return commandRepository.save(command);
    }

    public List<Command> getAllCommandByPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return commandRepository.findAll(pageable);
    }
}
