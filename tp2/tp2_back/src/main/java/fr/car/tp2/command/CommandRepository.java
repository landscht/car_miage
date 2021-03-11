package fr.car.tp2.command;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends CrudRepository<Command,Long> {

    List<Command> findAll(Pageable p);
    List<Command> findAllByUserId(Long userId);


}
