# Utilizar una imagen base de Maven para construir el proyecto
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Utilizar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY --from=build /app/target/eureka-server-*.jar eureka-server.jar

RUN apt-get update && apt-get install -y curl

ENTRYPOINT ["java","-jar","/eureka-server.jar"]