FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM bellsoft/liberica-runtime-container:jdk-21-slim-musl

WORKDIR /app

COPY --from=builder /app/target/spring-blog-0.0.1-SNAPSHOT.jar /app/spring-blog-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/spring-blog-0.0.1-SNAPSHOT.jar"]