FROM maven:3.3-jdk-8-onbuild
FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/intellect-boot-cud-jar.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
