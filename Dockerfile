# Stage 1: Build with OpenJDK 21 and Gradle Wrapper
FROM openjdk:21-slim AS builder
WORKDIR /demo
# Copy project files including the Gradle wrapper
COPY . .
# Ensure the Gradle wrapper is executable
RUN chmod +x gradlew
# Build the project using the Gradle wrapper
RUN ./gradlew clean build --no-daemon -x test

# Stage 2: Create the runtime image using OpenJDK 21
FROM openjdk:21-slim
WORKDIR /demo
# Copy the built jar file from the builder stage (adjust path if needed)
COPY --from=builder /demo/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]