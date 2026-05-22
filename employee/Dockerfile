FROM ubuntu:22.04 AS base
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install -y openjdk-17-jdk maven && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN mvn clean package -DskipTests=false

FROM amazoncorretto:17.0.11-alpine
WORKDIR /app
COPY --from=base /app/target/*.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]