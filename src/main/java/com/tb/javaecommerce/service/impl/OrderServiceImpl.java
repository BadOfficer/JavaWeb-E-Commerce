package com.tb.javaecommerce.service.impl;

import com.tb.javaecommerce.domain.Order;
import com.tb.javaecommerce.domain.OrderItem;
import com.tb.javaecommerce.dto.order.OrderRequestDto;
import com.tb.javaecommerce.dto.order.OrderResponseDto;
import com.tb.javaecommerce.service.OrderService;
import com.tb.javaecommerce.service.mappers.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Order addOrder(OrderRequestDto orderRequestDto) {
        return createOrderMock(
                orderRequestDto.getConsumerName(),
                orderRequestDto.getAddress(),
                orderRequestDto.getEmail(),
                orderRequestDto.getOrderStatus(),
                orderMapper.toOrderItemList(orderRequestDto.getOrderItems()),
                orderRequestDto.getTotalPrice()
        );
    }

    private Order createOrderMock(String consumerName, String address, String email, String orderStatus, List<OrderItem> orderItems, double totalPrice) {
        return  Order.builder()
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
