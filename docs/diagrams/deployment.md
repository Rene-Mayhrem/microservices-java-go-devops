graph TD
    subgraph Kubernetes Cluster 
        subgraph Namespace: Ecommerce
            FE_Pod["Frontend pod"]
            Order_Pod["Order Service Pod"]
            Payment_Pod["Payment Service Pod"]
            Inventory_Pod[Inventory Service Pod]
            UserP_Pod[User Service Pod]
        end
    end

    subgraph AWS
        EKS[EKS Cluster]
        RDS[RDS Database]
        S3[S3 Storage]
        ELB[Load Balancer]
    end

    FE_Pdod-->ELB
    ELB --> FE_Pod
    Order_Pod --> RDS
    Payment_Pod --> RDS
    Inventory_Pod --> RDS
