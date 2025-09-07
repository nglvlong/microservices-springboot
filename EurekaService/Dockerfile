# Stage 1: Build JAR
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
