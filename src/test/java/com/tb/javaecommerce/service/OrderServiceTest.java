package com.tb.javaecommerce.service;

import com.tb.javaecommerce.dto.order.OrderItemDto;
import com.tb.javaecommerce.dto.order.OrderRequestDto;
import com.tb.javaecommerce.dto.order.OrderResponseDto;
import com.tb.javaecommerce.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Order Service Test")
@SpringBootTest(classes = {OrderServiceImpl.class})
public class OrderServiceTest {

    @Autowired
    @Spy
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto("Product1", 2, 20.0));
        orderItemDtoList.add(new OrderItemDto("Product2", 1, 10.0));

        orderRequestDto = OrderRequestDto.builder()
                .address("123 Test St")
                .consumerName("John Doe")
                .email("test@example.com")
                .totalPrice(50.0)
                .orderStatus("PENDING")
                .orderItems(orderItemDtoList)
                .build();
    }

    @Test
    public void testAddOrder() {
        OrderResponseDto order = orderService.addOrder(orderRequestDto);

        assertEquals(orderRequestDto.getConsumerName(), order.getConsumerName());
        assertEquals(orderRequestDto.getAddress(), order.getAddress());
        assertEquals(orderRequestDto.getEmail(), order.getEmail());
        assertEquals(orderRequestDto.getTotalPrice(), order.getTotalPrice());
        assertEquals(orderRequestDto.getOrderStatus(), order.getOrderStatus());
        assertEquals(orderRequestDto.getOrderItems().size(), order.getOrderItems().size());
    }
}

