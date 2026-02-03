# ===============================
# Build Stage
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only pom first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source
COPY src ./src

# Build application
RUN mvn clean package -DskipTests


# ===============================
# Run Stage
# ===============================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
