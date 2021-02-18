package fr.car.tp2.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @CrossOrigin
    @GetMapping("/inStock")
    public List<Product> getAllProductInStock(@RequestParam int page){
        return productService.getAllProductInStockByPage(page);
    }

    @CrossOrigin
    @GetMapping
    public List<Product> getAllProduct(@RequestParam int page){
        return productService.getAllProductByPage(page);
    }

    @CrossOrigin
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }



}
