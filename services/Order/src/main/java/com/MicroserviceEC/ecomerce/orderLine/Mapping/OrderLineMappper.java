package com.MicroserviceEC.ecomerce.orderLine.Mapping;

import com.MicroserviceEC.ecomerce.order.Dtos.OrderResponse;
import com.MicroserviceEC.ecomerce.order.Order;
import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineReqeust;
import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineResponse;
import com.MicroserviceEC.ecomerce.orderLine.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMappper {
    public OrderLine toOrderLine(OrderLineReqeust orderLineReqeust) {


        return  OrderLine.builder()
                .Id(orderLineReqeust.Id())
                .productid(orderLineReqeust.productId())
                .quantity(orderLineReqeust.quantity())
                .order(Order.builder()
                        .id(orderLineReqeust.orderId()).build())
                .build();
    }


    public OrderLineResponse toOrderLineResonse(OrderLine orderLine) {

        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
