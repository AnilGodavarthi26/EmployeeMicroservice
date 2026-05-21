FROM ubuntu:latest AS base
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install -y openjdk-17-jdk maven
RUN mvn clean package -DskipTests=false
EXPOSE 8080
COPY target/*.jar /app/

FROM openjdk:17-jdk:alpine
WORKDIR /app
COPY --from=base /app/target/*.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar"]