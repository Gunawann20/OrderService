FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/springboot-images-new.jar springboot-images-new.jar
LABEL authors="Gunawan"

ENTRYPOINT ["java","-jar","/inventory-service.jar"]
