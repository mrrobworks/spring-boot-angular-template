# https://hackernoon.com/efficient-development-with-docker-and-docker-compose-e354b4d24831
# https://docs.docker.com/compose/extends/
FROM openjdk:12-jdk-alpine
RUN apk update
RUN apk upgrade
RUN apk add bash
WORKDIR /tmp
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
#CMD java -jar -Dspring.profiles.active=ref app.jar
#CMD ["java", "-jar", "-Dspring.profiles.active=ref", "app.jar"]
#CMD ["java", "-jar", "app.jar"]