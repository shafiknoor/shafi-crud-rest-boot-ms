FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml clean package
FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
WORKDIR /code

ADD pom.xml /code/pom.xml  

# Adding source, compile and package into a fat jar
ADD src /code/src  
RUN mvn package

ADD target/intellect-boot-cud-jar.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]
