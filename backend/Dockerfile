# # Use uma imagem base do Maven com OpenJDK 17
FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install -y maven

RUN apt-get install -y openjdk-17-jdk

COPY pom.xml /usr/src/app/

COPY . /usr/src/app

WORKDIR /usr/src/app

RUN mvn clean package

ENTRYPOINT ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]


EXPOSE 8080
