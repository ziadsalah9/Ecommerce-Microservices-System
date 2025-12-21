package com.MicroserviceEC.ecomerce.Payment.payment.kafka_Notification;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class NoticficationProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendPaymentNotification(PaymentNotificationRequest paymentNotificationRequest)  {

  try {


      byte[] payload = objectMapper.writeValueAsBytes(paymentNotificationRequest);

      log.info("Sending Payment Notification Request to topic: " + paymentNotificationRequest);
      Message<byte[]> message = MessageBuilder.withPayload(payload)
              .setHeader(KafkaHeaders.TOPIC, "payment-topic")
              .build();

      kafkaTemplate.send(message);

      log.info("payment notification sent");
  }
  catch (Exception e) {
      log.error("payment notification failed", e);
  }
    }

}

