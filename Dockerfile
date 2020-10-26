FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
COPY . /opt/app/
ENTRYPOINT ["java", "-jar", "/opt/app/spring-boot-application.jar"]
