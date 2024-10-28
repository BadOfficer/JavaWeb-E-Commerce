package com.tb.javaecommerce.service.mappers;

import com.tb.javaecommerce.common.ProductStatus;
import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "status", source = "status", qualifiedByName = "toStatusDescription")
    ProductResponseDto toProductResponseDto(Product product);

    List<ProductResponseDto> toProductResponseDtoList(List<Product> productList);


    @Named("toStatusDescription")
    default String toStatusDescription(ProductStatus status) {
        return status.getDescription();
    }
}
