graph TD
    subgraph Frontend
        FE["Web app / React"]
    end

    subgraph Backend
        OrderService["OrderService - Java Spring Boot"]
        PaymentService["Payment Service - Java Spring Boot"]
        InventoryService["Inventory Service - Java Spring Boot"]
        UserService["User Service -Java Spring Boot"]
    end

    subgraph Messaging
        MQ["Message Broker: Rabbit MQ/Kafka"]
    end

    subgraph Databases
        OrderDB[(Order DB)]
        UserDB[(Users DB)]
        InventoryDB[(Inventory DB)]
    end

    FE --> |REST API| OrderService
    FE --> |REST API| UserService

    OrderService --> InventoryService
    OrderService --> PaymentService
    OrderService --> |Async events| MQ

    InventoryService --> InventoryDB
    OrderService --> OrderDB
    UserService --> UserDB