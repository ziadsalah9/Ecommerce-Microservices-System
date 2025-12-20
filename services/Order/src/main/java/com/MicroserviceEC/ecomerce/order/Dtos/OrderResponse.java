package com.MicroserviceEC.ecomerce.order.Dtos;

import com.MicroserviceEC.ecomerce.order.Enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId

) {
}
