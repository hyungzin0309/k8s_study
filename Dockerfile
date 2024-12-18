FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/sample.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]