package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.common.ProductStatus;
import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.ProductRequestDto;
import com.tb.javaecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> productList = new ArrayList<>(List.of(
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Galactic Crystal")
                    .description("A rare crystal found on the surface of Mars.")
                    .price(299.99)
                    .status(ProductStatus.IN_STOCK)
                    .build(),
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Zero-Gravity Boots")
                    .description("Advanced boots designed for optimal movement in zero-gravity environments.")
                    .price(149.50)
                    .status(ProductStatus.DISCONTINUED)
                    .build(),
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Lunar Dust Sample")
                    .description("Collected from the surface of the Moon, this dust sample is highly sought by collectors.")
                    .price(499.99)
                    .status(ProductStatus.OUT_OF_STOCK)
                    .build()));


    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProductById(String productId) {
        return productList.stream().filter(item -> item.getId().toString().equals(productId)).findFirst().orElse(null);
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .title(productRequestDto.getTitle())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .status(productRequestDto.getStatus())
                .build();
        productList.add(product);
        return  product;
    }

    @Override
    public Product updateProduct(ProductRequestDto productRequestDto, String id) {
        Product product = getProductById(id);

        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStatus(productRequestDto.getStatus());

        return product;
    }
}
