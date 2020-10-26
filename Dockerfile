FROM openjdk:8-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
FROM maven:3.3.9

RUN curl -sSL https://get.docker.com/ | sh

CMD ["/bin/bash"]
COPY . /opt/app
RUN mvn -f /opt/app/pom.xml package
ENTRYPOINT ["java","-jar","/opt/app/target/intellect-boot-cud-jar.jar"]

