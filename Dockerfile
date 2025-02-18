FROM openjdk:8-jdk-alpine3.7
ARG JAR_FILE=jar/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]