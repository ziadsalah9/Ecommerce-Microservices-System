package com.MicroserviceEC.ecomerce.order.payment;

import com.MicroserviceEC.ecomerce.order.Enums.PaymentMethod;
import com.MicroserviceEC.ecomerce.order.customer.CustomerRespone;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerRespone customer

) {
}
