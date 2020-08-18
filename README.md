## Introduction
This document outlines the details as well as best practices in developing spring boot based REST API application that evaluates if 2 cities are connected based on origin and destination inputs. This project also includes Swagger API documentation, UNIT testSuite, Monitoring metrics (API statistics), Docker containerization to be used in container based environments such as docker, kubernetes, pivotal cloud foundry etc.in the cloud.

This dcoument outlines 
> 1. Design Solution
> 2. Implementation
> 3. Test
> 4. Run
> 5. Management & Monitoring (via Spring Boot Actuators)
> 6. API Documentation with Swagger UI
> 7. Docker Containerization to be run with Container engines


 
## Prerequisites
To run this application, below software installations are required

> 1. *Java* 8 or above
> 2. *Maven* 

**NOTE** *If you are going to run Docker image, then docker engine installation is required*
 
## Design Solution

> The Proposed solution is using Graph traversal mechanism to identify if a path exists between two vertices. 
> The solution has 2 parts
> 1. Read the input data from a file stored in classpath. whose filename is mentioned in resource file application.properties
> 2. By using the Breadth First Search(BFS) traversal algorithm, iteratively find if 2 cities are connected 


## Implementation
this sample application is a  maven project with implementations for below.

> 1. Custom Exception handling
> 2. Swagger API annotations
> 3. Unit test cases
> 4. health, info and monitoring metrics of API traffic
> 5. Docker image creation to be used via docker engine or orchestrated via kubernetes

## Installation & Run
  
 1. To install and run goto the directory in which you want to install the project.
clone the github project via URL

```git
git clone https://github.com/bkpravee/mc_springboot_test.git

```
 2. do  a maven clean install to build the source code
```maven
mvn clean install

```
3. The UNIT testsuite can be executed by
```maven
mvn test

```

4. Run the application using maven Spring Boot plugin
```maven
mvn spring-boot:run 
```
  OR if you are going to run the executable jar from the command line 

```command line
java -jar target/city-connections-0.0.1-SNAPSHOT.jar

```
 
## API Testing
The API can be tested using an REST API testing tool or as it is a GET only operation it can also be tested via browser
 
```
http://localhost:8080/connected?origin=NEW YORK&destination=newark
or 

http://localhost:8080/connected?origin=boston&destination=newark
```
## Swagger UI
the Swagger UI API documentation can be seen a the below URL. The API testing also can be executed from swagger UI *try it now* option
```
http://localhost:8080/swagger-ui.html
```
## API monitoring/metrics
to activate monitoring, *spring-actuator* dependency is reqruied in *pom.xml*. (it is already added..) and also *application.properties* need to configured. if listener port is not configured monitoring is available at default server port.
```
management.server.port=8081
management.endpoints.web.exposure.include=*
management.endpoints.jmx.exposure.include=health,info,env,beans
```
the monitoring metrics/health/info are available at following URL locations
```
application health can be seen at 
http://localhost:8081/actuator/health

for metrics
http://localhost:8081/actuator/metrics

for info
http://localhost:8081/actuator/info

for loggers
http://localhost:8081/actuator/loggers

```

## Dockerization

the *DockerFile* is part of the source, to create a docker image run
```docker
docker build -t <image name> .
```

to the run the docker image from docker console at port 8888
```docker
docker run -p 8888:8080 <image name>
```


**NOTE** *For illustration purposes the log4j logs are written to STD console log..*, but can be isolated and directed to respective file Appenders
