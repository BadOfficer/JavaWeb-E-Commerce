package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.common.ProductCategory;
import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> productList = List.of(
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Galactic Crystal")
                    .description("A rare crystal found on the surface of Mars.")
                    .price(299.99)
                    .categories(List.of(ProductCategory.ARTIFACTS, ProductCategory.EQUIPMENT))
                    .build(),
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Zero-Gravity Boots")
                    .description("Advanced boots designed for optimal movement in zero-gravity environments.")
                    .price(149.50)
                    .categories(List.of(ProductCategory.EQUIPMENT, ProductCategory.ARTIFACTS))
                    .build(),
            Product.builder()
                    .id(UUID.randomUUID())
                    .title("Lunar Dust Sample")
                    .description("Collected from the surface of the Moon, this dust sample is highly sought by collectors.")
                    .price(499.99)
                    .categories(List.of(ProductCategory.MATERIALS, ProductCategory.ARTIFACTS))
                    .build());

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProductById(String productId) {
        return productList.stream().filter(item -> item.getId().toString().equals(productId)).findFirst().orElse(null);
    }
}
