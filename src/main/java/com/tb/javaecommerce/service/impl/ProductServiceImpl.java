package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.common.ProductStatus;
import com.tb.javaecommerce.domain.Category;
import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.product.ProductRequestDto;
import com.tb.javaecommerce.service.CategoryService;
import com.tb.javaecommerce.service.ProductService;
import com.tb.javaecommerce.service.exception.ProductNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;

    private final List<Product> productList = new ArrayList<>();

    public ProductServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;

        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Galactic Crystal")
                .description("A rare crystal found on the surface of Mars.")
                .price(299.99)
                .status(ProductStatus.IN_STOCK)
                .category(categoryService.findCategoryById(1))
                .build());
        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Zero-Gravity Boots")
                .description("Advanced boots designed for optimal movement in zero-gravity environments.")
                .price(149.50)
                .status(ProductStatus.DISCONTINUED)
                .category(categoryService.findCategoryById(2))
                .build());
        productList.add(Product.builder()
                .id(UUID.randomUUID())
                .title("Lunar Dust Sample")
                .description("Collected from the surface of the Moon, this dust sample is highly sought by collectors.")
                .price(499.99)
                .status(ProductStatus.OUT_OF_STOCK)
                .category(categoryService.findCategoryById(1))                .
                build());
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProductById(String productId) {
        return productList.stream().filter(item -> item.getId().toString()
                        .equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryService.findCategoryById(productRequestDto.getCategoryId());

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .title(productRequestDto.getTitle())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .status(productRequestDto.getStatus())
                .category(category)
                .build();
        productList.add(product);
        return product;
    }

    @Override
    public Product updateProduct(ProductRequestDto productRequestDto, String id) {
        Product product = getProductById(id);
        Category category = categoryService.findCategoryById(productRequestDto.getCategoryId());

        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStatus(productRequestDto.getStatus());
        product.setCategory(category);

        return product;
    }

    @Override
    public String deleteProduct(String id) {
        Product product = getProductById(id);
        productList.remove(product);
        return "Product with ID - " + id + " has been deleted";
    }
}
