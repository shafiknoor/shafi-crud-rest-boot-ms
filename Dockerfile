FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml clean package install
FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
RUN ls /opt/app
COPY /target/intellect-boot-cud-jar.jar /opt/app/intellect-boot-cud-jar.jar
ENTRYPOINT ["java", "-jar", "/opt/app/intellect-boot-cud-jar.jar"]
