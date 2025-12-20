package com.MicroserviceEC.ecomerce.product.Dtos;

import com.MicroserviceEC.ecomerce.product.Category;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(




                 Integer Id,
                @NotNull(message = "Product name is Required")
                 String Name,

                 @NotNull(message = "Product description is Required")

                 String Description,
                 @Positive(message = "Available Quantity should be Positive")
                 @NotNull(message = "Product Quantity is Required")

                 double availableQuantity,

                 @Positive(message = " Price should be Positive")

                 BigDecimal Price,

                 @NotNull(message = "Product Category is Required")

                 Integer category_id

) {
}
