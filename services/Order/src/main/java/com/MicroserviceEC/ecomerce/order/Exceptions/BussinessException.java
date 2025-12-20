package com.MicroserviceEC.ecomerce.order.Exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BussinessException extends RuntimeException {
//    public BussinessException(@NotNull(message = "Customer should be present") @NotEmpty(message = "Customer should be present") @NotBlank(message = "Customer should be present") String msg) {
//
//
//        // EqualsAndHashCode = true like   super(msg)
//
//    }


    private final String Msg;
}
