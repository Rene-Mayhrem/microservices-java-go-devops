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

```
ecommerce-microservices/
├── services/
│ ├── java/
│ │ ├── user-service/
│ │ └── order-service/
│ └── go/
│ ├── product-service/
│ ├── payment-service/
│ ├── cart-service/
│ └── notification-service/
├── infra/
│ ├── docker/
│ ├── k8s/
│ ├── terraform/
│ └── tilt/
├── gateway/
│ └── api-gateway/
├── monitoring/
│ ├── grafana/
│ └── prometheus/
└── ci-cd/
└── github-actions/
```
---

## 🚀 Workflow

1. Start with **Product Service (Go)** → practice Go basics.
2. Add **Order Service (Java)** → integrate with DB & transactions.
3. Introduce **Event-driven messaging** with Kafka/RabbitMQ.
4. Add **Tilt** → fast local dev & auto-redeploys.
5. Move to **Kubernetes** deployment.
6. Add **Monitoring & Logging**.
7. Experiment with **gRPC** between Go and Java services.
8. Setup **CI/CD with GitHub Actions**.
9. Define infra with **Terraform**.

---

## 🛠️ Tech Stack

- **Languages**: Go, Java (Spring Boot)
- **Databases**: PostgreSQL, Redis
- **Messaging**: Kafka / RabbitMQ
- **Containerization**: Docker
- **Orchestration**: Kubernetes (minikube, kind, or EKS)
- **Dev Tools**: Tilt, Terraform, Helm
- **CI/CD**: GitHub Actions / Jenkins
- **Monitoring**: Prometheus, Grafana
- **Logging**: ELK / Loki

---

## 📚 Learning Focus

- **Go:** concurrency, error handling, testing, gRPC.
- **Java:** DDD, Spring Boot best practices, transactional workflows.
- **DevOps:** CI/CD pipelines, IaC, monitoring, observability.
- **Architecture:** microservices design, polyglot systems, API Gateway, service-to-service comms.

---

## 🔮 Roadmap

- [ ] Implement Product Service in Go  
- [ ] Implement Order Service in Java  
- [ ] Setup Docker Compose for local development  
- [ ] Add Kafka for messaging  
- [ ] Setup Tilt for local iterative dev  
- [ ] Deploy services to Kubernetes  
- [ ] Add monitoring stack (Prometheus, Grafana)  
- [ ] Implement CI/CD pipeline  
- [ ] Provision infra with Terraform  

---

## 📖 Author’s Note

This project is built primarily as a **learning playground** to strengthen skills as a **Java backend developer**, explore **Go**, and gain hands-on **DevOps experience**.
