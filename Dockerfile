FROM openjdk:8 as build
MAINTAINER Harsh Pratyush
ADD . .
CMD ./gradlew build

FROM openjdk:8-jdk-alpine
RUN addgroup -S graphql-server && adduser -S graphql-server -G graphql-server
USER graphql-server:graphql-server

ARG JAR_FILE=build/libs/*.jar
COPY --from=build ${JAR_FILE} graphql-server.jar

ENTRYPOINT ["java", "-jar", "graphql-server.jar"]