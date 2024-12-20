FROM openjdk:24-ea-21-jdk-slim-bullseye
WORKDIR /app

COPY ./EAProjectSecond/src/ /app/src
COPY ./EAProjectSecond/pom.xml /app/pom.xml

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

EXPOSE 8081
CMD ["mvn", "spring-boot:run"]
