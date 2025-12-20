package com.MicroserviceEC.ecomerce.Payment.payment.Mapping;

import com.MicroserviceEC.ecomerce.Payment.payment.Dtos.requestPayment;
import com.MicroserviceEC.ecomerce.Payment.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class paymentMapper {


    public Payment toPayment(requestPayment request) {


        if (request == null) {
            return null;
        }
       return
        Payment.builder()

                .id(request.Id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())

                .build();
    }
}
