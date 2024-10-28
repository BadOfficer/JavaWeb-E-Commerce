package com.tb.javaecommerce.dto;

import com.tb.javaecommerce.common.ProductStatus;
import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private ProductStatus status;
}
