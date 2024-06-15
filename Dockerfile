FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/OrderService-0.0.1-SNAPSHOT.jar order-service.jar
LABEL authors="Gunawan"

ENTRYPOINT ["java","-jar","/inventory-service.jar"]
