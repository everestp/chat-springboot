# Use the official lightweight OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/*.jar app.jar

# Expose the port your app will run on

EXPOSE 8080
EXPOSE 27017
EXPOSE 27018

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]