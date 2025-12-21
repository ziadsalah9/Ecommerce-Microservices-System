package com.MicroserviceEC.ecomerce.Notification;

import com.MicroserviceEC.ecomerce.Notification.Kafka.NotificationConsumer;
import com.MicroserviceEC.ecomerce.Notification.notification.NotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
public class NotificationApplication implements CommandLineRunner {

    private NotificationConsumer notificationConsumer;
    public NotificationApplication(NotificationConsumer notificationConsumer) {
        this.notificationConsumer = notificationConsumer;
    }

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        notificationConsumer.sendemail();
    }
}
