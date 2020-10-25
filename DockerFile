FROM java:8
EXPOSE 8080
ADD /target/intellect-boot-cud-jar-1.0.0-SNAPSHOT.jar dockerdemo.jar
ENTRYPOINT [“java”, “-jar”, “dockerdemo.jar”]
