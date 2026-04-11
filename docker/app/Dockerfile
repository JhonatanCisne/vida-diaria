#Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

#Copiar el archivo de las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

#Copiar codigo fuente y compilarlo
COPY src ./src
RUN mvn clean package -DskipTests

#Ejecutar
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

#Copiar el JAR generado desde la etapa de compilacion
COPY --from=build /app/target/*.jar app.jar

#Exponer el puerto
EXPOSE 8080

#Comando para iniciar la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]