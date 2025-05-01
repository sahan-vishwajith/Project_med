# Stage 1: Build with OpenJDK 21 and Gradle Wrapper
FROM openjdk:21-slim AS builder
# Set working directory to the folder that contains gradlew
WORKDIR /demo/Spring-Security-Tutorial-main
# Copy all project files (ensure the build context is set correctly)
COPY . .
# Debug: list files to verify gradlew is present
RUN ls -la
# Ensure the Gradle wrapper is executable
RUN chmod +x gradlew
# Build the project using the Gradle wrapper (skip tests)
RUN ./gradlew clean build --no-daemon -x test

# Stage 2: Create the runtime image using OpenJDK 21
FROM openjdk:21-slim
WORKDIR /demo
# Copy the built jar file from the builder stage.
# Adjust the jar name if it differs.
COPY --from=builder /demo/Spring-Security-Tutorial-main/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
