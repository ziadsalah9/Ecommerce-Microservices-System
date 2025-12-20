package com.MicroserviceEC.ecomerce.Payment.payment.controllers;

import com.MicroserviceEC.ecomerce.Payment.payment.Dtos.requestPayment;
import com.MicroserviceEC.ecomerce.Payment.payment.services.paymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class Paymentcontroller {

    private final paymentService _service;

    @PostMapping
    public ResponseEntity<Integer> createPayment( @RequestBody requestPayment request) {

        return ResponseEntity.ok(_service.createPayment(request));

    }




}
