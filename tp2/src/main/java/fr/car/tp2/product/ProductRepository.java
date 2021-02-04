package fr.car.tp2.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findByStockGreaterThan(int nb, Pageable p);
    List<Product> findAll(Pageable p);

}
