FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml clean package
FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
COPY . /opt/app
RUN ls /opt/app/target
CMD ["java", "-jar", "/opt/app/target/intellect-boot-cud-jar.jar"]
