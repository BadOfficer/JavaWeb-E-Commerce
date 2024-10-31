package com.tb.javaecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CategoryDto {
    @NotBlank(message = "Title is mandatory")
    String title;

    @NotBlank(message = "Description is mandatory")
    String description;
}
