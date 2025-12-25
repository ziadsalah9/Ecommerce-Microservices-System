# Ecommerce-Microservices-System

MicroserviceEcommerce

MicroserviceEcommerce is an advanced e-commerce API built using a microservices architecture. The system is divided into several domains:

Customer Domain – manages customer information.

Product Domain – manages products available for purchase.

Order Domain – handles order creation and processing.

Payment Domain – manages payments for orders.

Notification Domain – handles notifications related to orders and payments.

Architecture & Features
Inter-service Communication

Orders communicate with the Customer service using OpenFeign to retrieve customer details.

Orders communicate with the Product service using RestTemplate to select products.

Event-driven Notifications

After an order is processed, Kafka sends notifications to the Notification service.

Payments trigger notifications as well, ensuring real-time updates.

Centralized Configuration

All microservices load their configuration from a Config Server, enabling easy updates and consistency.

Service Discovery

Eureka is used for service registration and discovery, enabling microservices to locate each other dynamically.

Supports load balancing via Spring Cloud Ribbon and ensures resilience through self-preservation mode.

API Gateway

Provides a single entry point to the system and routes requests to the appropriate microservice.

Containerized Environment

Docker is used to run MongoDB, PostgreSQL, Kafka, MailDev, Zipkin, and Keycloak for a fully isolated environment.

Tracing & Monitoring

Zipkin is integrated to track requests across services for debugging and performance monitoring.

Highlights

Fully decoupled microservices architecture without the need for RPC-based communication.

Suitable for scalable, resilient, and maintainable e-commerce applications.

Demonstrates modern microservices patterns: event-driven communication, centralized configuration, service discovery, and API gateway.
