package com.tb.javaecommerce.domain;

import com.tb.javaecommerce.common.ProductCategory;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Product {
    UUID id;
    String title;
    String description;
    Double price;
    List<ProductCategory> categories;
}
