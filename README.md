**后端开发**:

- 构建和配置微服务架构，包含多个独立服务，如用户服务、书籍服务、借阅服务和邮件服务。
- 实现服务发现和注册，使用Spring Cloud Consul管理微服务实例。
- 使用OpenFeign实现RPC调用，在服务间高效通信。
- 通过Spring Cloud CircuitBreaker（Resilience4j）实现服务容错和降级处理。
- 使用Spring Cloud Stream集成RabbitMQ消息队列，处理异步消息和任务调度。
- 采用Spring Cloud Gateway实现API网关，进行请求路由和身份验证。
- 在存储层使用JPA和MyBatis进行数据持久化和操作。
- 使用JWT进行用户认证和授权，确保请求安全。
- - **功能实现**:
       - 实现书籍的查询、借阅、归还和管理功能，用户可以通过系统方便地管理和借阅图书。
       - 使用RabbitMQ进行消息队列的邮件发送，用户借阅和归还图书后会收到相应的通知邮件。
       - 采用JWT进行用户认证和授权，确保系统安全性。
       - 通过Spring Cloud Gateway进行请求路由和负载均衡，提升系统的性能和可扩展性。
       - 实现了限流和熔断机制，确保系统在高并发情况下的稳定性。
- **前端链接https://github.com/michealmachine/RentBookFront**
