FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
COPY ./target/intellect-boot-cud-jar.jar /opt/app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
