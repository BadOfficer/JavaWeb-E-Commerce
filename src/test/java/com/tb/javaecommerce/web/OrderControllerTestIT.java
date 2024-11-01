package com.tb.javaecommerce.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.javaecommerce.domain.Category;
import com.tb.javaecommerce.domain.Order;
import com.tb.javaecommerce.dto.order.OrderItemDto;
import com.tb.javaecommerce.dto.order.OrderRequestDto;
import com.tb.javaecommerce.dto.order.OrderResponseDto;
import com.tb.javaecommerce.service.CategoryService;
import com.tb.javaecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    OrderRequestDto orderRequestDto;

    OrderResponseDto orderResponseDto;

    @BeforeEach
    public void init() {
        List<OrderItemDto> orderItems = new ArrayList<>(List.of(
                new OrderItemDto("Product1", 2, 20.0),
                new OrderItemDto("Product2", 1, 10.0)
        ));

        orderRequestDto = OrderRequestDto.builder()
                .consumerName("John")
                .email("john@gmail.com")
                .orderStatus("PENDING")
                .totalPrice(150)
                .orderItems(orderItems)
                .address("123 Test St")
                .build();

        orderResponseDto = OrderResponseDto.builder()
                .id(UUID.fromString("92cbf62b-abab-451b-9e8f-b092ee27cb62"))
                .address(orderRequestDto.getAddress())
                .consumerName(orderRequestDto.getConsumerName())
                .email(orderRequestDto.getEmail())
                .orderItems(orderRequestDto.getOrderItems())
                .totalPrice(orderRequestDto.getTotalPrice())
                .build();
    }

    @Test
    public void shouldPlaceOrder() throws Exception {
        Mockito.when(orderService.addOrder(orderRequestDto)).thenReturn(orderResponseDto);

        ResultActions response = mockMvc.perform(post("/api/v1/orders/add-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequestDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
