FROM openjdk:8-jdk-alpine
MAINTAINER Harsh Pratyush

RUN addgroup -S graphql-server && adduser -S graphql-server -G graphql-server
USER graphql-server:graphql-server

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} graphql-server.jar

ENTRYPOINT ["java", "-jar", "graphql-server.jar"]