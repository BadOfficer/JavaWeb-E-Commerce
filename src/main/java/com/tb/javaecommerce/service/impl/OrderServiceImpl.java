package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.dto.order.OrderItemDto;
import com.tb.javaecommerce.dto.order.OrderRequestDto;
import com.tb.javaecommerce.dto.order.OrderResponseDto;
import com.tb.javaecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        return createOrderMock(
                orderRequestDto.getConsumerName(),
                orderRequestDto.getAddress(),
                orderRequestDto.getEmail(),
                orderRequestDto.getOrderStatus(),
                orderRequestDto.getOrderItems(),
                orderRequestDto.getTotalPrice()
        );
    }

    private OrderResponseDto createOrderMock(String consumerName, String address, String email, String orderStatus, List<OrderItemDto> orderItems, double totalPrice) {
        return OrderResponseDto.builder()
                .id(UUID.randomUUID())
                .consumerName(consumerName)
                .address(address)
                .email(email)
                .orderStatus(orderStatus)
                .orderItems(orderItems)
                .totalPrice(totalPrice)
                .build();
    }
}
