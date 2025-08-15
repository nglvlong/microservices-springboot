# MicroserviceSource - Spring Boot Microservices

## 📌 Giới thiệu
Dự án này là ví dụ **Microservices Architecture** sử dụng **Spring Boot**.  
Hệ thống gồm nhiều service độc lập giao tiếp thông qua **Eureka Service Discovery** và được điều phối bởi **API Gateway**.

## 🏗 Kiến trúc tổng quan

```
[Client] → [API Gateway] → [Eureka Server] → [User Service]
                                       ↘ → [Order Service]
```

- **Eureka Server**: Service registry giúp quản lý danh sách các service.
- **API Gateway**: Làm nhiệm vụ điều phối, định tuyến, bảo mật.
- **User Service**: Quản lý thông tin người dùng.
- **Order Service**: Quản lý đơn hàng.

## 🛠 Công nghệ sử dụng
- Java 17
- Spring Boot 3
- Spring Cloud
- Spring Cloud Netflix (Eureka, Gateway)
- Maven

## 📂 Cấu trúc dự án
```
MicroserviceSource/
│
├── eureka-server/           # Service registry
│   ├── src/main/java/...  
│   └── application.yml
│
├── api-gateway/             # Gateway định tuyến request
│   ├── src/main/java/...
│   └── application.yml
│
├── user-service/            # Quản lý người dùng
│   ├── src/main/java/...
│   └── application.yml
│
├── order-service/           # Quản lý đơn hàng
│   ├── src/main/java/...
│   └── application.yml
│
└── pom.xml                  # POM cha
```

## ⚙ Các cấu hình chính

### 1. Eureka Server (`eureka-server`)
**application.yml**
```yaml
server:
  port: 8761

spring:
  application:
    name: eureka-server

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

**Main class**
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

---

### 2. API Gateway (`api-gateway`)
**application.yml**
```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/users/**
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/orders/**
```

---

### 3. User Service (`user-service`)
**application.yml**
```yaml
server:
  port: 8081

spring:
  application:
    name: user-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

### 4. Order Service (`order-service`)
**application.yml**
```yaml
server:
  port: 8082

spring:
  application:
    name: order-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

## 🚀 Cách chạy dự án

1. **Clone project**
```bash
git clone https://github.com/<your-username>/MicroserviceSource.git
```

2. **Build toàn bộ project**
```bash
mvn clean install -DskipTests
```

3. **Chạy các service** (theo thứ tự):
```bash
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd user-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
```

4. **Truy cập Eureka Dashboard**
```
http://localhost:8761
```

---
