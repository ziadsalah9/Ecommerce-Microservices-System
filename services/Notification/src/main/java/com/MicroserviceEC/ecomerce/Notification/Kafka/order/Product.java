package com.MicroserviceEC.ecomerce.Notification.Kafka.order;

import java.math.BigDecimal;

public record Product (


        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity

){



}
