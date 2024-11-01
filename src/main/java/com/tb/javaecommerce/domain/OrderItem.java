package com.tb.javaecommerce.domain;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class OrderItem {
    String product;
    int quantity;
    double price;
}
