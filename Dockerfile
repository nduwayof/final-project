FROM openjdk:8-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/payment-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} payment-service-0.0.1-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","payment-service-0.0.1-SNAPSHOT.jar"]

