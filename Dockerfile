FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/my-spring-boot-app.jar my-spring-boot-app.jar

CMD ["java", "-jar", "my-spring-boot-app.jar"]