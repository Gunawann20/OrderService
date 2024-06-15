FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar
LABEL authors="Gunawan"

ENTRYPOINT ["java","-jar","/inventory-service.jar"]