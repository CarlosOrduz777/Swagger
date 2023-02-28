package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(productsService.save(product));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productsService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        try{
            return new ResponseEntity<>(productsService.findById(id).get(),HttpStatus.OK);
        }catch (Exception e){
            throw new ProductNotFoundException(id);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto nproduct, @PathVariable String id ) {
        Optional<Product> oldproduct = productsService.findById(id);
        Product newproduct = new Product(nproduct);
        if(!oldproduct.isEmpty()){
            productsService.update(newproduct,id);
            productsService.save(oldproduct.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ProductNotFoundException(id);
        }
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id) {
        Optional<Product> oldproduct = productsService.findById(id);
        if(!oldproduct.isEmpty()){
            productsService.deleteById(id);
        }else{
            throw new ProductNotFoundException(id);
        }
    }
}
