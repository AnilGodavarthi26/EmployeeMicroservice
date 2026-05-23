# Build Stage
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom first for dependency caching
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests=false

# Runtime Stage
FROM amazoncorretto:17-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]