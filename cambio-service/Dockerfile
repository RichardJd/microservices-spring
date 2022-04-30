FROM openjdk:17-jdk-slim
ARG JAR_FILE=cambio-service/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]