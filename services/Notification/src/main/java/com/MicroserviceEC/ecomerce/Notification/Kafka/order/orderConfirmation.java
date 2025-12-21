package com.MicroserviceEC.ecomerce.Notification.Kafka.order;

import com.MicroserviceEC.ecomerce.Notification.Kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record orderConfirmation (

        String orderRefrence,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products


){
}
