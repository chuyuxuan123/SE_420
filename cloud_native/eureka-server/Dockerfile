FROM openjdk:8-jdk-alpine

VOLUME /tmp

MAINTAINER chuyuxuan

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/app.jar"]