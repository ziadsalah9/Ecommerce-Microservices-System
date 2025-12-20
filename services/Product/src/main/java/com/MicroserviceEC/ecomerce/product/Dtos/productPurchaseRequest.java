package com.MicroserviceEC.ecomerce.product.Dtos;

import jakarta.validation.constraints.NotNull;

public record productPurchaseRequest(

        @NotNull(message = "product is required")
        Integer product_id,
        @NotNull(message = "quantity is required")
        double quantity


) {
}
