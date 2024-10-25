# Microservices Architecture Project

This project demonstrates the development of a microservices architecture using Spring Boot and Java. It includes various components that collaborate to provide a resilient and scalable system for sending emails through SendGrid. The key elements of this project are:

- Eureka Server for service discovery.
- API Gateway (Entry Point) as the central access point for clients.
- Email Microservice utilizing SendGrid for email delivery.
- Circuit Breaker pattern implemented with Resilience4j to handle communication failures.

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Components](#components)
- [Eureka Server](#eureka-server)
- [API Gateway (Entry Point)](#api-gateway-entry-point)
- [Email Microservice](#email-microservice)
- [Circuit Breaker](#circuit-breaker)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Flow](#project-flow)
- [Important Note](#important-note)
- [Contributing](#contributing)
- [License](#license)

## Overview

The project showcases how to build a microservices-based system where an API Gateway handles client requests and routes them to appropriate microservices. The Email Microservice is responsible for sending emails using SendGrid. The system employs the Circuit Breaker pattern to enhance resilience, preventing failures in one service from cascading to others.

## Architecture

The architecture consists of:

- Clients interacting with the system through the API Gateway.
- API Gateway serving as the Entry Point and routing requests to microservices.
- Eureka Server enabling service discovery and registration.
- Email Microservice handling email sending functionality.
- Circuit Breaker mechanisms to handle service failures gracefully.

## Components

### Eureka Server

The Eureka Server acts as a Service Registry where all microservices register themselves. It allows services to discover and communicate with each other without hardcoding network locations.

**Features:**
- Service Registration and Discovery.
- Load Balancing support.
- High Availability through clustering.

### API Gateway (Entry Point)

The API Gateway serves as the single Entry Point for all client requests. It implements the API Gateway pattern, providing a unified interface and handling cross-cutting concerns like authentication, logging, and routing.

**Responsibilities:**
- Routing requests to appropriate microservices.
- Handling protocol translation if necessary.
- Implementing security measures.

### Email Microservice

The Email Microservice is responsible for sending emails using SendGrid. It exposes RESTful endpoints that the API Gateway calls to initiate email sending.

**Features:**
- Integration with SendGrid API for email delivery.
- Error handling and logging.
- Registration with Eureka Server for discoverability.

### Circuit Breaker

The Circuit Breaker pattern is implemented using Resilience4j to prevent cascading failures and improve system resilience. It monitors calls between the API Gateway and the Email Microservice, opening the circuit if the failure threshold is exceeded.

**Benefits:**
- Prevents additional load on failing services.
- Provides fallback mechanisms.
- Enhances overall system stability.

## Technologies Used

- Java 11+
- Spring Boot
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Resilience4j
- SendGrid API
- Maven for build and dependency management
- Docker (optional, for containerization)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven installed
- SendGrid Account with API Key
- Git for version control

**Important:** The email functionality requires a SendGrid account and a valid API Key. Please set up an account with SendGrid and obtain an API Key to enable email sending.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/microservices-project.git
```
2. Navigate to the project directory:
```bash
cd microservices-project
```
3. Build the projects using Maven:
```bash
mvn clean install
```
4. Set up SendGrid API Key:
```bash
sendgrid.api.key=YOUR_SENDGRID_API_KEY
```

5. Send a test email request:
    - Use a tool like Postman to send a POST request to the API Gateway endpoint:

   ```http
   POST http://localhost:8081/api/send-email
   ```
   - Request Body:
   ```http
    {
    
        "subject":"Message de Spam",
        "receiver":"receiverEmail",
        "message":"Message de spam em direto",
        "title":"Message Importante",
        "type":"Email",
        "requester":"originEmail"
    }
   ```
## Project Flow

1. **Client Request:**
    - The client sends a request to the API Gateway to send an email.

2. **API Gateway Routing:**
    - The API Gateway routes the request to the Email Microservice based on the route configuration.

3. **Service Discovery:**
    - The Email Microservice is registered with the Eureka Server.
    - The API Gateway uses Eureka to discover the instance of the Email Microservice.

4. **Email Sending:**
    - The Email Microservice processes the request and uses SendGrid API to send the email.
    - It logs the result and handles any errors.

5. **Circuit Breaker Activation:**
    - If the Email Microservice fails to respond, the Circuit Breaker in the API Gateway intercepts the failure.
    - After a defined threshold, the Circuit Breaker opens, and the API Gateway can provide a fallback response.

## Important Note

- **SendGrid Integration:**
    - To enable email sending functionality, you must create a SendGrid account and obtain an API Key.
    - Replace the placeholder in the Email Microservice’s `application.properties` or `application.yml` file with your actual API Key.
    - Be aware of SendGrid’s usage policies and limitations, especially if using a free trial account.

- **Circuit Breaker Configuration:**
    - The Circuit Breaker settings are configured in the `application.properties` or `application.yml` files.
    - Adjust the parameters like failure rate threshold, wait duration, and permitted number of calls according to your needs.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for review.

## License

This project is licensed under the MIT License.

