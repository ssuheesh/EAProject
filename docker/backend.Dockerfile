FROM openjdk:24-ea-21-jdk-slim-bullseye
WORKDIR /app

COPY ./EAProjectMain/src/ /app/src
COPY ./EAProjectMain/pom.xml /app/pom.xml

RUN apt-get update && apt-get install -y maven

EXPOSE 8080
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-Dspring.profiles.active=aws'"]
