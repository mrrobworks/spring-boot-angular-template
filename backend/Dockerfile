FROM openjdk:12-jdk-alpine
RUN apk update
RUN apk upgrade
RUN apk add bash
WORKDIR /tmp
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
CMD java -jar app.jar