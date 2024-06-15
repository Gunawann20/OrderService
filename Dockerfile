FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/OrderService.jar OrderService.jar
LABEL authors="Gunawan"

ENTRYPOINT ["java","-jar","/inventory-service.jar"]
