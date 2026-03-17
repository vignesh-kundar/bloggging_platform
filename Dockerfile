FROM bellsoft/liberica-runtime-container:jdk-21-slim-musl

WORKDIR /app

COPY target/spring-blog-0.0.1-SNAPSHOT.jar /app/spring-blog-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/spring-blog-0.0.1-SNAPSHOT.jar"]