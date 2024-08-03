FROM maven:3-eclipse-temurin-22-alpine AS build
ENV VERSION=0.0.1-SNAPSHOT

COPY . /app
WORKDIR /app
RUN mvn package

FROM eclipse-temurin:22-jre-alpine
COPY --from=build /app/target/hermes.jar /app/hermes.jar
CMD ["java", "-jar", "/app/hermes.jar"]