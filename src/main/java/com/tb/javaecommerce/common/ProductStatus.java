package com.tb.javaecommerce.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductStatus {
    IN_STOCK("Product is available and in stock."),
    OUT_OF_STOCK("Product is currently out of stock."),
    DISCONTINUED("Product is no longer available.");

    private final String description;
}
