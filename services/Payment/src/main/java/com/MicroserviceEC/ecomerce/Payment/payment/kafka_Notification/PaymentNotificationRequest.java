package com.MicroserviceEC.ecomerce.Payment.payment.kafka_Notification;

import com.MicroserviceEC.ecomerce.Payment.payment.Enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
