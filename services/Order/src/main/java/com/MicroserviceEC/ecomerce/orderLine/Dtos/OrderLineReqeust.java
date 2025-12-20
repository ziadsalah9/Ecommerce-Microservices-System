package com.MicroserviceEC.ecomerce.orderLine.Dtos;

public record OrderLineReqeust(

        Integer Id,
        Integer orderId,
        Integer productId,
        double quantity

) {
}
