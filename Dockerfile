# BIULD Stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

COPY .     ./blue-wallet

# remove skip test after adding the tests.
RUN mvn -B -f blue-wallet/blue-wallet-api/pom.xml clean install -DskipTests

RUN mvn -B -f blue-wallet/pom.xml clean package -DskipTests

# RUN Stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /build/blue-wallet/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
