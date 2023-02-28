package org.adaschool.api.controller.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct() {
        //TODO implement this method
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct() {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct() {
        //TODO implement this method

        return ResponseEntity.ok().build();
    }
}
