# Etapa 1: Construcción del JAR utilizando Maven
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y construye el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación con una imagen ligera de OpenJDK
FROM openjdk:17-jdk-alpine
VOLUME /tmp
WORKDIR /app

# Copia el JAR construido desde la etapa de build
COPY --from=build /app/target/cita-service.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
