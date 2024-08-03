FROM maven:3-eclipse-temurin-22-alpine AS build

COPY . /app
WORKDIR /app
RUN mvn package

FROM eclipse-temurin:22-jre-alpine
COPY --from=build /app/target/hermes.jar /app/hermes.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/hermes.jar"]