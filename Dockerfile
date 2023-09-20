FROM openjdk:17

WORKDIR /app
ADD target/melifera-revamp-0.0.1-SNAPSHOT.jar melifera-revamp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","melifera-revamp-0.0.1-SNAPSHOT.jar"]


