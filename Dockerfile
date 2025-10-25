# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
COPY backend ./backend
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/TinyTasks-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
