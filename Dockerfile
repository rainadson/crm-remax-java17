# ===== BUILD =====
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests clean package

# ===== RUN =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia somente o jar executável (bootJar)
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
