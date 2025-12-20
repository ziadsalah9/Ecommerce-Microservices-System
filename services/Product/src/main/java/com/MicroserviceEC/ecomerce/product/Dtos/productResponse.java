package com.MicroserviceEC.ecomerce.product.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record productResponse(


        Integer Id,
        String Name,

        String Description,

        double availableQuantity,


        BigDecimal Price,


        Integer category_id,
        String categoryName,
        String categoryDescription


) {
}
