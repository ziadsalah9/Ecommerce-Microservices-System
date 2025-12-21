package com.MicroserviceEC.ecomerce.order.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class orderProducer {

    private final KafkaTemplate<String,byte[]> _kafkaTemplate;
    private final ObjectMapper objectMapper;
    public void sendConfirmation(orderConfirmation orderconfirmation){


        try {
            byte[] payload = objectMapper.writeValueAsBytes(orderconfirmation);


            log.info("Sending order confirmation ...");
            Message<byte[]> message = MessageBuilder.withPayload(payload)
                    .setHeader(KafkaHeaders.TOPIC, "order-topic").build();

            _kafkaTemplate.send(message);

            log.info("Order confirmation has been sent");

        }
        catch (Exception e){
            log.error("Error while sending order confirmation",e);
        }
    }

}
