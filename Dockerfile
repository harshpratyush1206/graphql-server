FROM openjdk:8-jdk-alpine
MAINTAINER Harsh Pratyush
COPY build/libs/graphql-server-* graphql-server.jar
ENTRYPOINT ["java", "-jar", "graphql-server.jar"]