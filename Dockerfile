# BIULD Stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

COPY .     ./blue-wallet

RUN mvn -B -f blue-wallet/blue-wallet-api/pom.xml clean install -DskipTests=true dependency:go-offline

RUN mvn -B -f blue-wallet/pom.xml clean package -DskipTests=true dependency:go-offline

# RUN Stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /build/blue-wallet/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
