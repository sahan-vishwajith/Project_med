# ---- Stage 1: Build with JDK 21 ----
FROM eclipse-temurin:21-jdk-jammy AS builder

# Use a simple, clean workdir
WORKDIR /app

# Copy Gradle wrapper & config first (better layer caching)
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
# If you have more *.gradle or gradle.properties, copy them too:
# COPY gradle.properties ./
# COPY *.gradle ./

# Make wrapper executable
RUN chmod +x gradlew

# Optionally warm up Gradle (not mandatory)
# RUN ./gradlew --no-daemon help

# Now copy source
COPY src ./src

# Build the jar (skip tests if you want)
RUN ./gradlew clean bootJar --no-daemon -x test

# ---- Stage 2: Runtime with JRE 21 ----
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the fat jar from the builder stage
# Adjust if your jar name is different
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
