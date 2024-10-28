package com.tb.javaecommerce.domain;

import com.tb.javaecommerce.common.ProductStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Product {
    UUID id;
    String title;
    String description;
    Double price;
    ProductStatus status;
}
