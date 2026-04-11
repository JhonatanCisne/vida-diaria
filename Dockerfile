# --Compilación ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar el pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# --- Imagen de Ejecución  ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar solo el JAR generado desde el paso anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer los puertos (8080 HTTP y 9090 gRPC)
EXPOSE 8080
EXPOSE 9090

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
