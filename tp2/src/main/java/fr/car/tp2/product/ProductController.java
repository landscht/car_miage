package fr.car.tp2.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProductInStock(@RequestParam int page){
        return productService.getAllProductInStockByPage(page);
    }

    @GetMapping
    public List<Product> getAllProduct(@RequestParam int page){
        return productService.getAllProductByPage(page);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

}
