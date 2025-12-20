package com.MicroserviceEC.ecomerce.order.kafka;

import com.MicroserviceEC.ecomerce.order.Enums.PaymentMethod;
import com.MicroserviceEC.ecomerce.order.customer.CustomerRespone;
import com.MicroserviceEC.ecomerce.order.products.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public record orderConfirmation(
        String orderRefrence,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerRespone customer,
        List<PurchaseResponse> products
) {
}
