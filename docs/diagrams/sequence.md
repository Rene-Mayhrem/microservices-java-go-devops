sequenceDiagram
    participant Customer
    participant FE
    participant OrderService
    participant InventoryServcie
    participant PaymentService
    participant MQ
    participant OrderDB

    Customer->>FE: Place Order
    FE->>OrderService: POST /orders
    OrderService->>InventoryService: Check inventory
    InventoryService->>OrderService: Inventory OK
    OrderService->>PaymentService: Process payment
    PaymentService->>OrderService: Payment Success
    OrderService->>OrderDB: Save order
    OrderService->>MQ: Publish OrderCreated Event
    MQ->>InventoryService: Update Inventory (async)