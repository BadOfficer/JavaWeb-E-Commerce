package com.tb.javaecommerce.web;

import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.ProductDto;
import com.tb.javaecommerce.service.ProductService;
import com.tb.javaecommerce.service.mappers.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products.stream()
                .map(ProductMapper.INSTANCE::toProductDto)
                .toList());
    }
}
