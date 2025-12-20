package com.MicroserviceEC.ecomerce.product.Dtos;

import java.math.BigDecimal;

public record productPurchaseResponse(
        Integer product_id,
        String Name,
        String Description,

        BigDecimal Price,
        double quantity

) {
}
