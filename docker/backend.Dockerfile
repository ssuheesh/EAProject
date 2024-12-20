FROM openjdk:24-ea-23-jdk-bullseye
WORKDIR /app

COPY ./EAProjectMain /app

RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

EXPOSE 8080
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-Dspring.profiles.active=aws'"]
