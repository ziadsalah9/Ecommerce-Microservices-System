package com.MicroserviceEC.ecomerce.Excptions;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundExption extends RuntimeException {

    private final String msg;
}
