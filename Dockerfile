# Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Set the working directory
WORKDIR /app
# Copy the pom.xml and dependencies first to leverage Docker caching
COPY pom.xml ./
RUN mvn dependency:go-offline -B
# Copy the entire project and build it
COPY . ./
RUN mvn clean package -DskipTests
# Use a lightweight JDK runtime image for running the application
FROM openjdk:21-jdk-slim
# Set the working directory
WORKDIR /app
# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar
# Expose application port
EXPOSE 8080
# Default (can be overrided)
ENV applicationVersion=1.0.0
ENV TestMode=false
# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
