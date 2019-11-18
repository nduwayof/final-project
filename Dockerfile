FROM openjdk:8-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/notification-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} notification-service-0.0.1-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","notification-service-0.0.1-SNAPSHOT.jar"]

