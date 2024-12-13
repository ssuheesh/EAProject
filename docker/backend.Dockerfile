FROM openjdk:24-ea-21-jdk-slim-bullseye
WORKDIR /app

COPY ./src /app/src
COPY ./pom.xml /app/pom.xml

RUN apt-get update && apt-get install -y maven

EXPOSE 8080
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true'"]
