# Etapa 1: Construir o .jar
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem para rodar o .jar
FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]