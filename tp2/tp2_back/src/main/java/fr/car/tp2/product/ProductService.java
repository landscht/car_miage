package fr.car.tp2.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProductInStockByPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findByStockGreaterThan(0,pageable);
    }

    public List<Product> getAllProductByPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAll(pageable);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }
}
