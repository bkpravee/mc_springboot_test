#Create a docker image using openJDK 8
FROM openjdk:8-jdk-alpine
RUN addgroup -S springTest && adduser -S testUser -G springTest
USER testUser:springTest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} city_connections.jar
ENTRYPOINT ["java","-jar","/city_connections.jar"]