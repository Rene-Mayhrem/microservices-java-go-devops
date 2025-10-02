# 🛒 Microservice E-Commerce Project

This project is a **microservice-based e-commerce application** designed to learn and practice:

* **Backend Development** with **Java Spring Boot**
* **DevOps** practices with **Docker, Kubernetes, Terraform, CI/CD**
* **Cloud Deployment** on **AWS** (EKS, RDS, S3, Load Balancer)
* **Event-Driven Architectures** using **Kafka/RabbitMQ**

---

## 🚀 Goals

* Build a **realistic e-commerce app** using microservices.
* Learn **containerization & orchestration** with Docker & Kubernetes.
* Explore **cloud deployment** with AWS and Infrastructure as Code.
* Practice **CI/CD pipelines** and modern DevOps workflows.

---

## 🗂️ System Architecture

High-level overview of the system:

```mermaid
graph TD
    subgraph Frontend
        FE[Web App - React/Next.js]
    end

    subgraph Backend Services
        OrderService[Order Service - Spring Boot]
        PaymentService[Payment Service - Spring Boot]
        InventoryService[Inventory Service - Spring Boot]
        UserService[User Service - Spring Boot]
        NotificationService[Notification Service - Spring Boot]
    end

    subgraph Messaging
        MQ[Message Broker - Kafka/RabbitMQ]
    end

    subgraph Databases
        OrderDB[(Orders DB)]
        UserDB[(Users DB)]
        InventoryDB[(Inventory DB)]
        PaymentDB[(Payments DB)]
    end

    FE -->|REST API| OrderService
    FE -->|REST API| UserService

    OrderService --> InventoryService
    OrderService --> PaymentService
    OrderService -->|Async Event| MQ

    InventoryService --> InventoryDB
    OrderService --> OrderDB
    UserService --> UserDB
    PaymentService --> PaymentDB

    MQ --> NotificationService
    MQ --> InventoryService
```

---

## 📦 Order Workflow – Sequence Diagram

How an order is placed and processed:

```mermaid
sequenceDiagram
    participant Customer
    participant FE as Frontend
    participant OS as Order Service
    participant IS as Inventory Service
    participant PS as Payment Service
    participant MQ as Message Broker
    participant DB as Order DB

    Customer->>FE: Place Order
    FE->>OS: POST /orders
    OS->>IS: Check inventory
    IS-->>OS: Inventory OK
    OS->>PS: Request payment
    PS-->>OS: Payment success
    OS->>DB: Save order
    OS->>MQ: Publish OrderCreated event
    MQ-->>IS: Update inventory (async)
    MQ-->>NotificationService: Send confirmation
```

---

## ☁️ Deployment Architecture

Containerized deployment with **Kubernetes on AWS EKS**:

```mermaid
graph TD
    subgraph AWS Cloud
        subgraph EKS[EKS Cluster]
            FE_Pod[Frontend Pod]
            Order_Pod[Order Service Pod]
            Payment_Pod[Payment Service Pod]
            Inventory_Pod[Inventory Service Pod]
            User_Pod[User Service Pod]
            Notification_Pod[Notification Service Pod]
            MQ_Pod[Kafka/RabbitMQ Pod]
        end

        subgraph Databases
            RDS1[(Orders DB - RDS)]
            RDS2[(Users DB - RDS)]
            RDS3[(Inventory DB - RDS)]
            RDS4[(Payments DB - RDS)]
        end

        S3[S3 Bucket - Assets/Media]
        ELB[Elastic Load Balancer]
    end

    Customer --> ELB
    ELB --> FE_Pod

    FE_Pod --> Order_Pod
    FE_Pod --> User_Pod

    Order_Pod --> RDS1
    User_Pod --> RDS2
    Inventory_Pod --> RDS3
    Payment_Pod --> RDS4

    Order_Pod --> MQ_Pod
    MQ_Pod --> Notification_Pod
    MQ_Pod --> Inventory_Pod
```

---

## 🔄 Event/Data Flow

Asynchronous communication with **event-driven architecture**:

```mermaid
graph TD
    OrderService -->|OrderCreated| MQ
    MQ --> InventoryService
    MQ --> NotificationService
    MQ --> PaymentService
    MQ --> AnalyticsService[Analytics/Monitoring]
```

---

## 🛠️ Tech Stack

* **Backend:** Java 17, Spring Boot, Maven
* **Frontend:** React/Next.js
* **Databases:** PostgreSQL/MySQL (via AWS RDS)
* **Messaging:** Kafka / RabbitMQ
* **Infrastructure:** Docker, Kubernetes (EKS), Terraform
* **CI/CD:** GitHub Actions / Jenkins
* **Cloud Services:** AWS RDS, S3, EKS, ELB


