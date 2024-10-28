package com.tb.javaecommerce.service;

import com.tb.javaecommerce.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String productId);
}
