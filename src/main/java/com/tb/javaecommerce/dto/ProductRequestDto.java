package com.tb.javaecommerce.dto;

import com.tb.javaecommerce.common.ProductStatus;
import com.tb.javaecommerce.dto.validation.Extended;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@GroupSequence({ProductRequestDto.class, Extended.class})
public class ProductRequestDto {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 50, message = "Title cannot exceed 100 characters")
    String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description;

    @NotNull(message = "Price is mandatory")
    Double price;

    ProductStatus status;
}
