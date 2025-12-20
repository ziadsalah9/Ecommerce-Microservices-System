package com.MicroserviceEC.ecomerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (

        String id,
        @NotNull(message = "first name is required")
         String firstName,
        @NotNull(message = "Last name is required")
         String lastName,

        @NotNull(message = "email is required")
        @Email(message = "is not a valid email address  ")
         String email,
        Address address

){
}
