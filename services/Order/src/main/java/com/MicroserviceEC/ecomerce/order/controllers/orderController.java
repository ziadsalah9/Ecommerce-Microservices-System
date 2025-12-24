package com.MicroserviceEC.ecomerce.order.controllers;

import com.MicroserviceEC.ecomerce.order.Dtos.OrderResponse;
import com.MicroserviceEC.ecomerce.order.Dtos.orderRequest;
import com.MicroserviceEC.ecomerce.order.Services.orderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class orderController {


    private final orderService _service;

    @PostMapping
    public ResponseEntity<Integer> createOrder( @RequestBody @Valid orderRequest request){

      return ResponseEntity.ok(_service.createOrder(request));
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(_service.findAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(_service.findById(orderId));
    }




}
