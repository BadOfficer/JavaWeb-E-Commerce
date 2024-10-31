package com.tb.javaecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CategoryDto {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description cannot exceed 100 characters")
    String description;
}
