FROM maven:3.8.4-openjdk-17-slim AS builder
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE} ./app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
