package com.tb.javaecommerce.dto.order;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderResponseDto {
    UUID id;
    String consumerName;
    String address;
    String email;
    double totalPrice;
    String orderStatus;
}
