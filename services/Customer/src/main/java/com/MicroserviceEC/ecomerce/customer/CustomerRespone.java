package com.MicroserviceEC.ecomerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRespone(

         String id,
         String firstName,
         String lastName,
         String email,
         Address address

){
}
