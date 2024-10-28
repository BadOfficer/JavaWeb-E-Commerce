package com.tb.javaecommerce.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    ARTIFACTS("Rare and valuable artifacts from various planets"),
    EQUIPMENT("Equipment for space travel and exploration"),
    MATERIALS("Resources and materials found in space");

    private final String description;
}
