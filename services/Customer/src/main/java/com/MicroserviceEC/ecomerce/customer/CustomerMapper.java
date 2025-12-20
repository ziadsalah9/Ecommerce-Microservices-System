package com.MicroserviceEC.ecomerce.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer( CustomerRequest request) {

        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerRespone toCustomerRespone(Customer customer) {

        return new CustomerRespone(
                customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress()
        );
    }
}
