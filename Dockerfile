FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Install git and clone the repository
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/Nithin0620/PasswordStrengthChecker.git .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Final runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/password-strength-checker-[0-9].[0-9].[0-9]*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]