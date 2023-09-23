# Banking Service 
## Using Spring Boot Microservices
#### Get started with the project from docker-compose folder (It is recommended to use default profile as this is something which I actively update.) - Start the project by using `docker compose up`.

- Service discovery using Spring Cloud Eureka.
- Api gateway server using Spring Cloud gateway.
- Client side load balancer using Spring Cloud Load Balancer. 
- Load configs based on profile using Spring Cloud config server.
- Netflix Feign Client to look up for a service b/w microservices.
- Fault Tolerance using Resilience4j (Circuit breaker, Fallback, Retry).
- Micrometer for distributed tracing.
- Zipkin for data visualization and aggregating logs.
