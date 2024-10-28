package com.tb.javaecommerce.web;

import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.ProductRequestDto;
import com.tb.javaecommerce.dto.ProductResponseDto;
import com.tb.javaecommerce.service.ProductService;
import com.tb.javaecommerce.service.mappers.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toProductResponseDtoList(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.toProductResponseDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(productMapper.toProductResponseDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable String id) {
        Product product = productService.updateProduct(productRequestDto, id);
        return ResponseEntity.ok(productMapper.toProductResponseDto(product));
    }
}
