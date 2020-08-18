## Introduction
This document outlines the details as well as best practices in REST API that evaluates if 2 cities are connected based in origin and destination inputs using spring boot. This project also includes Swagger API documentation, UNIT testSuite, Docker containerization to be used in container based environments in the cloud as well as application monitoring as well as metrics.

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

1) *Java* 8 or above
2) *Maven* 

**NOTE** *If you are going to run Docker image, then docker engine installation is required*
 
## Design Solution

> The Proposed solution is using Graph traversal mechanism to identify if a path exists between two vertices. 
> The solution has 2 parts
> 1) Read the city data from file stored in classpath. whose location is mentioned via application.properties file
> 2) By using the BreadthFirstSearch(BFS) traversal algorithm, iteratively find if 2 cities are connected 


## Implementation
this sample application is a  maven project which include
	1. Custom error handling
	2. Swagger API annotations
	3. Unit test cases
	4. health, info and monitoring metrics of API traffic
	5. Docker image creation to be used via docker engine or orchestrated via kubernetes

## Installation & Run
  
 1. To install and run goto the directory in which you want to install the project.
clone the github project via URL

```git
> git clone https://github.com/bkpravee/mc_springboot_test.git

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
To run the sample application, run the executable jar located at *./oauth2-client-sample/target/oauth-factset-client-0.0.1-SNAPSHOT.jar*

1. 
```command line
	java -jar .\oauth2-client-sample\target\oauth2-client-sample-0.0.1-SNAPSHOT.jar

```
## Swagger UI
the Swagger UI API documentation can be seen a the below URL. The API testing also can be executed from swagger UI *try it now* option
```
http://localhost:8081/swagger-ui.html
```

**NOTE** *For illustration purposes the API response is written to console log..*
