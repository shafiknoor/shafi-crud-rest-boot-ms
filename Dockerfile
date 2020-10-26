FROM java:8-jre-alpine
EXPOSE 8080
RUN mkdir /app
COPY target/intellect-boot-cud-jar.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
