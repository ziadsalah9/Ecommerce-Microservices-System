package com.MicroserviceEC.ecomerce.order.Mapping;

import com.MicroserviceEC.ecomerce.order.Dtos.OrderResponse;
import com.MicroserviceEC.ecomerce.order.Dtos.orderRequest;
import com.MicroserviceEC.ecomerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(orderRequest request) {

            if (request == null) {
                return null;
            }
        return Order.builder()
                .id(null)
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {

        return  new OrderResponse(       order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId());
    }
}
