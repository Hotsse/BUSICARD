# Use JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]