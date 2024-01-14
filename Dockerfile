FROM openjdk:17-jdk-alpine
MAINTAINER https://github.com/PaHod
COPY build/libs/*SNAPSHOT.jar ./song-service.jar
#EXPOSE ${SONG_SERVICE_PORT}
ENTRYPOINT ["java", "-jar", "song-service.jar"]
