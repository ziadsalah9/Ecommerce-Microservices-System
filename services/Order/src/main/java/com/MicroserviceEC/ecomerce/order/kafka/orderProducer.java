package com.MicroserviceEC.ecomerce.order.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class orderProducer {

    private final KafkaTemplate<String,orderConfirmation> _kafkaTemplate;
    public void sendConfirmation(orderConfirmation orderconfirmation){

        log.info("Sending order confirmation ...");
        Message<orderConfirmation> message = MessageBuilder.withPayload(orderconfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic").build();

        _kafkaTemplate.send(message);
    }

}
