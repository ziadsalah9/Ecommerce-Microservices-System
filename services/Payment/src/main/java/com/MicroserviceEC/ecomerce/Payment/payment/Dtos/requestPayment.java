package com.MicroserviceEC.ecomerce.Payment.payment.Dtos;

import com.MicroserviceEC.ecomerce.Payment.payment.Enums.PaymentMethod;

import java.math.BigDecimal;

public record  requestPayment (

        Integer Id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

        ){
}
