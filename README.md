# Banking Service 
## Using Spring Boot Microservices
#### Get started with the project from docker-compose folder (It is recommended to use default profile as this is something which I actively update.) - Start the project by using `docker compose up`.

- Service discovery using [**Spring Cloud Eureka**](https://cloud.spring.io/spring-cloud-netflix/reference/html/).
- Api gateway server using [**Spring Cloud Gateway**](https://spring.io/projects/spring-cloud-gateway).
- Client side load balancer using [**Spring Cloud Load Balancer**](https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/loadbalancer.html). 
- Load configs based on profile using [**Spring Cloud Config Server**](https://spring.io/projects/spring-cloud-config).
- [**Netflix Feign Client**](https://spring.io/projects/spring-cloud-openfeign#overview) to look up for a service b/w microservices.
- Fault Tolerance using [**Resilience4j**](https://resilience4j.readme.io/docs) (Circuit breaker, Fallback, Retry).
- [**Micrometer**](https://micrometer.io/docs/tracing) for distributed tracing.
- [**Zipkin**](https://zipkin.io/) for data visualization and aggregating logs.
