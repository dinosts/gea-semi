FROM openjdk:8-jdk-alpine AS build
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package

FROM openjdk:8-jdk-alpine AS run
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

RUN mkdir git-repositories
RUN mkdir jars
COPY --from=build jars/*.jar jars/



RUN apk add --no-cache git