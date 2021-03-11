package fr.car.tp2.command;

import fr.car.tp2.product.ProductService;
import fr.car.tp2.purchase.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;
    private final ProductService productService;

    public Command saveCommand(Command command){
        command.setDate(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        for(Purchase purchase : command.getPurchases()){
            productService.actualizeStock(purchase.getProduct().getId(), purchase.getQuantity());
        }
        return commandRepository.save(command);
    }

    public List<Command> getAllCommandByUser(Long userId) {
        return commandRepository.findAllByUserId(userId);
    }

    public List<Command> getAllCommandByPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return commandRepository.findAll(pageable);
    }
}
