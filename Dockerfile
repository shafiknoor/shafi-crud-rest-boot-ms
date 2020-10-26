FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml clean package install
FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
COPY . /opt/app/
RUN ls /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/spring-boot-application.jar"]
