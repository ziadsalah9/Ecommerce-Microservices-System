package com.MicroserviceEC.ecomerce.Notification.Kafka;

import com.MicroserviceEC.ecomerce.Notification.Email.EmailService;
import com.MicroserviceEC.ecomerce.Notification.Kafka.order.orderConfirmation;
import com.MicroserviceEC.ecomerce.Notification.Kafka.order.Product;
import com.MicroserviceEC.ecomerce.Notification.Kafka.payment.PaymentNotificationRequest;
import com.MicroserviceEC.ecomerce.Notification.notification.Notification;
import com.MicroserviceEC.ecomerce.Notification.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.MicroserviceEC.ecomerce.Notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.MicroserviceEC.ecomerce.Notification.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;



    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(byte[] messageBytes) throws MessagingException {
        try {
            var orderConfirmation = objectMapper.readValue(messageBytes, orderConfirmation.class);

            log.info("Consuming the message from order-topic Topic:: {}", orderConfirmation);

            repository.save(
                    Notification.builder()
                            .type(ORDER_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .build()
            );

            var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customer().email(),
                    customerName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.orderRefrence(),
                    orderConfirmation.products()
            );

        } catch (Exception e) {
            log.error("Error processing order-topic message", e);
        }
    }


    public  void  sendemail() throws MessagingException {
        emailService.sendOrderConfirmationEmail(
                "ziads5933@gmail.com",
                "Ziad Salah",
                BigDecimal.valueOf(100),
                "ORDER123",
                List.of(new Product(10, "email","", BigDecimal.valueOf(50) ,20))
        );
    }


    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(byte[] messageBytes) throws MessagingException {

        try {
            var paymentConfirmation =
                    objectMapper.readValue(messageBytes, PaymentNotificationRequest.class);

            log.info(
                    "Consuming the message from payment-topic Topic:: {}",
                    paymentConfirmation
            );

            repository.save(
                    Notification.builder()
                            .type(PAYMENT_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                           // .(paymentConfirmation)
                            .build()
            );

            var customerName =
                    paymentConfirmation.customerFirstName() + " " +
                            paymentConfirmation.customerLastName();

            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );

        } catch (Exception e) {
            log.error("Error while consuming payment-topic message", e);
        }
    }


}


@Configuration
 class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}