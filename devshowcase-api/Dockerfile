# Etapa 1: build da aplicacao com Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final, so com o jar e o JRE (mais leve)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# O Render define a variavel PORT automaticamente; a aplicacao precisa escutar nela
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

