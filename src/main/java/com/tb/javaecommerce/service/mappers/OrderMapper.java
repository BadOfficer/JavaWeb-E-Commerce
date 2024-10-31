package com.tb.javaecommerce.service.mappers;

import com.tb.javaecommerce.domain.Order;
import com.tb.javaecommerce.domain.OrderItem;
import com.tb.javaecommerce.dto.order.OrderItemDto;
import com.tb.javaecommerce.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<OrderItem> toOrderItemList(List<OrderItemDto> orderItemDto);
    OrderResponseDto toOrderResponseDto(Order order);
}
