# Stage 1: Build React (Vite)
FROM node:18 AS frontend-build
WORKDIR /app/frontend

# 복사 및 설치
COPY src/main/app/package.json src/main/app/package-lock.json ./
RUN npm install

# React 애플리케이션 빌드
COPY src/main/app .
RUN npm run build

# Stage 2: Build Spring Boot
FROM eclipse-temurin:21-jdk AS backend-build
WORKDIR /app/backend

# Maven 빌드
COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 3: Combine Backend and Frontend
FROM eclipse-temurin:21-jdk AS final-stage
WORKDIR /app

# Spring Boot JAR 복사
COPY --from=backend-build /app/backend/target/*.jar app.jar

# React 빌드 결과 복사
COPY --from=frontend-build /app/frontend/dist ./static

# Expose Port
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]