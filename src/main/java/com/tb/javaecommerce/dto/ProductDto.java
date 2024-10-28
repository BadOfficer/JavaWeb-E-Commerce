package com.tb.javaecommerce.dto;

import lombok.Value;

import java.util.List;

@Value
public class ProductDto {
    String title;
    String description;
    double price;
    List<String> categories;
}
