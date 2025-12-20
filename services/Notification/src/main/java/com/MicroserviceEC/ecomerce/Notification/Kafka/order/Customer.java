package com.MicroserviceEC.ecomerce.Notification.Kafka.order;


public record Customer(

        String id,
        String firstName,
        String lastName,
        String email


) {
}
