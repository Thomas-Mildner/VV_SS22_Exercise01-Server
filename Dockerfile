FROM gradle:7-jdk-alpine AS build
MAINTAINER Thomas Mildner
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-alpine
EXPOSE 8080
RUN apk update && apk add bash
RUN apk add --update curl && rm -rf /var/cache/apk/*
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/Exercise01_Server-0.0.1-SNAPSHOT.jar /app/exercise01-server-spring-boot-application.jar
ENTRYPOINT ["java", "-jar","/app/exercise01-server-spring-boot-application.jar"]