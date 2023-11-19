FROM openjdk:21-jdk

COPY target/players.jar players.jar

ENTRYPOINT ["java", "-jar", "/players.jar"]