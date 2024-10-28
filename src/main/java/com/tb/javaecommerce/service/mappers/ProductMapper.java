package com.tb.javaecommerce.service.mappers;

import com.tb.javaecommerce.domain.Product;
import com.tb.javaecommerce.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toProductDto(Product product);
}
