package com.MicroserviceEC.ecomerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService _service ;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody @Valid CustomerRequest request) {


        return ResponseEntity.ok(_service.createCustomer (request));
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {

        _service.UpdateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public  ResponseEntity<List<CustomerRespone>> findAllCustomers(){


        return ResponseEntity.ok( _service.findAll());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id){

        return ResponseEntity.ok(_service.ExistsById(id));
    }

//    @GetMapping("/{customer_id}")
//    public ResponseEntity<CustomerRespone> findCustomerById(@PathVariable String id){
//
//        return ResponseEntity.ok(_service.findById(id));
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable String id){

        _service.DeleteCustomer(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CustomerRespone> findById(@PathVariable String id){
        return ResponseEntity.ok(_service.findById(id));
    }

}
