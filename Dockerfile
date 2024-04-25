FROM openjdk:17
COPY target/*.jar employee-service.jar
ENTRYPOINT ["java","-jar","employee-service.jar"]
