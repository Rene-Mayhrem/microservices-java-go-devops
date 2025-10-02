graph TD
    OrderService --> |Order Created| MQ
    MQ --> InventoryService
    MQ --> NotificationService[Email/SMS Notifications]