package com.tb.javaecommerce.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String id;
    private String title;
    private String description;
    private Double price;
    private String status;
}
