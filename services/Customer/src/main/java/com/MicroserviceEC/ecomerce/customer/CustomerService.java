package com.MicroserviceEC.ecomerce.customer;


import com.MicroserviceEC.ecomerce.Excptions.CustomerNotFoundExption;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustmoerRepository _CustomerRepo;
    private final CustomerMapper mapper;


    public String createCustomer(@Valid CustomerRequest request) {

        var customer = _CustomerRepo.save(mapper.toCustomer(request));
    return  customer.getId();
    }

    public void UpdateCustomer(@Valid CustomerRequest request) {
        var customer = _CustomerRepo.findById(request.id()).orElseThrow(()->new CustomerNotFoundExption(
                "Cant update this Customer :: because Customer not found with id " + request.id()
        ));


        mergeCustomer(customer,request);
        _CustomerRepo.save(customer);


    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());


        }
        if(StringUtils.isNotBlank(request.lastName())) {
        customer.setLastName(request.lastName());}

        if(StringUtils.isNotBlank(request.email())) {

        customer.setEmail(request.email());}

        if (customer.getAddress()!=null) {
        customer.setAddress(request.address());}

    }

    public  List<CustomerRespone> findAll() {
        try {


            return _CustomerRepo.findAll().stream().map(mapper::toCustomerRespone).collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new CustomerNotFoundExption(e.getMessage());
        }


    }

    public @Nullable Boolean ExistsById(String id) {

        return _CustomerRepo.findById(id).isPresent();
    }

    public @Nullable CustomerRespone findById(String id) {

        return _CustomerRepo.findById(id).map(mapper::toCustomerRespone).orElseThrow(()->new CustomerNotFoundExption("" +
                "customer not found with id " + id));
    }

    public void DeleteCustomer(String id) {

        _CustomerRepo.deleteById(id);
    }
}
