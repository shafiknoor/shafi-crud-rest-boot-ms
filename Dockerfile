FROM maven:3.3-jdk-8-onbuild
FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
WORKDIR /code

ADD pom.xml /code/pom.xml  

# Adding source, compile and package into a fat jar
ADD src /code/src  
RUN mvn -f pom.xml clean package

ADD target/intellect-boot-cud-jar.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]
