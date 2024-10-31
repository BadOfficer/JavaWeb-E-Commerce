package com.tb.javaecommerce.service;

import com.tb.javaecommerce.domain.Order;
import com.tb.javaecommerce.dto.order.OrderRequestDto;

public interface OrderService {
    Order addOrder(OrderRequestDto orderRequestDto);
}
