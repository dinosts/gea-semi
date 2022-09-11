FROM openjdk:8-jdk-alpine


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

RUN mkdir git-repositories
RUN mkdir jars
COPY jars/*.jar jars/

RUN apk add --no-cache git