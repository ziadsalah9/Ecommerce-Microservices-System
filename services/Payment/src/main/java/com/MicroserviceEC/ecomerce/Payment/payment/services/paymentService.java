package com.MicroserviceEC.ecomerce.Payment.payment.services;

import com.MicroserviceEC.ecomerce.Payment.payment.Dtos.requestPayment;
import com.MicroserviceEC.ecomerce.Payment.payment.Mapping.paymentMapper;
import com.MicroserviceEC.ecomerce.Payment.payment.Repository.paymentRepository;
import com.MicroserviceEC.ecomerce.Payment.payment.kafka_Notification.NoticficationProducer;
import com.MicroserviceEC.ecomerce.Payment.payment.kafka_Notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class paymentService {

    private final paymentRepository _paymentRepo;
    private  final paymentMapper mapper;
    private final NoticficationProducer _noticficationProducer;
    public @Nullable Integer createPayment(requestPayment request) {

        // save payment
        var payment = _paymentRepo.save(mapper.toPayment(request));


        // send notification producer to kafka

        _noticficationProducer.sendPaymentNotification(

                new PaymentNotificationRequest(
                        request.orderReference(),request.amount(),request.paymentMethod(),
                        request.customer().firstname(),request.customer().lastname(),request.customer().email()
                )
        );


        return payment.getId();

    }
}
