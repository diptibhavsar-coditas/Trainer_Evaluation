package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController<ProductUpdateRequest> {

    private final ProductService productService;

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestParam Long userId,
                                                 @RequestParam String role,
                                                 @PathVariable Long productId,
                                                 @RequestBody ProductUpdateRequest request) {

        Product updatedProduct = productService.updateProduct(
                userId,
                role,
                productId,
                request
        );

        return ResponseEntity.ok(updatedProduct);
    }
}