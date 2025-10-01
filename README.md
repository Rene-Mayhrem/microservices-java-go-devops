# 🛒 E-Commerce Microservices (Java + Go + DevOps)

This project is a **polyglot e-commerce microservices system** built to learn and practice:
- **Go (Golang) backend development**
- **Java (Spring Boot) backend development**
- **DevOps & Cloud-Native practices** (Docker, Kubernetes, CI/CD, Tilt, Terraform, Monitoring)

The system simulates a real-world e-commerce platform where services are distributed, containerized, and orchestrated.

---

## 🎯 Goals

- Gain **hands-on experience with Go** by building microservices.
- Strengthen **Java backend expertise** with Spring Boot.
- Practice **DevOps workflows**: containers, orchestration, infrastructure as code, CI/CD, monitoring, and observability.
- Learn to integrate **polyglot microservices** (Go + Java) into one ecosystem.
- Build a **portfolio project** that demonstrates backend + DevOps skills.

---

## 🏗️ Architecture

### Core Services
| Service              | Language | Responsibilities |
|----------------------|----------|------------------|
| **User Service**     | Java (Spring Boot) | Authentication, registration, profile mgmt |
| **Order Service**    | Java (Spring Boot) | Order lifecycle, transactions, business logic |
| **Product Service**  | Go       | CRUD products, categories, inventory |
| **Payment Service**  | Go       | Payment processing, retries, refunds |
| **Cart Service**     | Go       | Session cart management, Redis caching |
| **Notification Service** | Go   | Send emails, SMS, async notifications |

### Supporting Components
- **API Gateway**: NGINX or Spring Cloud Gateway
- **Database**: PostgreSQL
- **Cache**: Redis
- **Messaging**: Kafka or RabbitMQ
- **Monitoring**: Prometheus + Grafana
- **Logging**: ELK / Loki

---

## 📂 Project Structure

