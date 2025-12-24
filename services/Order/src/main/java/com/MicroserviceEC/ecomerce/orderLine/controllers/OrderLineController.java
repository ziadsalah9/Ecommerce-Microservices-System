package com.MicroserviceEC.ecomerce.orderLine.controllers;


import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineReqeust;
import com.MicroserviceEC.ecomerce.orderLine.Dtos.OrderLineResponse;
import com.MicroserviceEC.ecomerce.orderLine.Services.OrderLineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService _service;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> getAllOrderLinesById(@PathVariable("order-id") Integer orderId) {

        return ResponseEntity.ok(_service.findAllOrderlinesByOrderId(orderId));
    }


}
