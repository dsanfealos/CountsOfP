FROM openjdk:23-jdk-slim
ARG JAR_FILE=target/counts_of_p-0.0.1.jar
COPY ${JAR_FILE} api_counts_of_p.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api_counts_of_p.jar"]