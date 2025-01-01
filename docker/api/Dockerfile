# Use JDK 21 base image
FROM eclipse-temurin:21-jdk

# Install Korean fonts (Nanum Fonts)
RUN apt-get update && apt-get install -y \
    fonts-nanum \
    && apt-get clean

# Refresh font cache
RUN fc-cache -fv

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/*.jar app.jar

# Create the h2 directory
RUN mkdir -p h2

# Copy the test_db file into the h2 directory
COPY ./h2/test_db.mv.db ./h2/test_db.mv.db

# Expose application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]