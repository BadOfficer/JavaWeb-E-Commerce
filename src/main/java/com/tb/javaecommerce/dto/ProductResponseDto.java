package com.tb.javaecommerce.dto;

import lombok.Value;

@Value
public class ProductResponseDto {
    String id;
    String title;
    String description;
    Double price;
    String status;
}
