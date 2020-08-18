## Introduction
This document outlines the details as well as best practices in REST API that evaluates if 2 cities are connected based in origin and destination inputs using spring boot. This project also includes Swagger API documentation, UNIT testSuite, Docker containerization to be used in container based environments in the cloud as well as application monitoring as well as metrics.

This dcoument outlines 
> 1. Design Solution
> 2. Implementation
> 3. Test
> 4. Run
> 5. Management & Monitoring (via Spring Boot Actuators)
> 6. Documentation with Swagger
> 7. Docker Containerization to be run with Container engines

 
## Prerequisites
 1) To run this application, *Java* 8+ and *Maven* software installations are required
 2) clone from GitHub URL
 
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
  
 1. first run maven command from the root folder *oauth2-client-parent*
 
  ```maven
  mvn clean package spring-boot:repackage
 ```
  This compiles all the maven modules and packages them into executable jar files as well dependent pom and runtime libraries
  command line application is ready to run, the executable jar file is located at *./oauth2-client-sample/target/oauth-factset-client-0.0.1-SNAPSHOT.jar*

 **NOTE** *spring-boot:repackage* it is an assembly plugin used to assemble required binaries to make an executable application, but it is not a spring-boot application.
 successful maven packaging will show below log message in the command/terminal window
 ````maven log
	[INFO] oauth2-client-parent ............................... SUCCESS [  0.865 s]
	[INFO] oauth2-client-sdk .................................. SUCCESS [  2.905 s]
	[INFO] oauth2-client-sample ............................... SUCCESS [  1.321 s]
	[INFO] ------------------------------------------------------------------------
````
After successful maven packaging, follow below setup steps before executing the sample test cases..
  
  2. Register a client application in dev portal, and download the resultant JSON
  
  3. please set the downloaded JSON file path as an environment variable *oauth2.client.file.path*
  
	for example
	```environment variable
	oauth2.client.file.path=C:\Downloads\client.json
	```
  4. make sure the download JSON is validated and contains following elments
  
```json

```
 5. In case if client application in dev portal is created wih 'use own signing keys' option, the JSON should have an additional element 
 *private_key_pem_path*. Its value should contain the full file path to the private key location.
 
 **NOTE** It can be seen in the below JSON, that the *private* value is null, because only *public* is used during client registraion in dev portal. Private key is essential to sign token requests
 
```json

```

## Test Run
To run the sample application, run the executable jar located at *./oauth2-client-sample/target/oauth-factset-client-0.0.1-SNAPSHOT.jar*

1. 
```command line
	java -jar .\oauth2-client-sample\target\oauth2-client-sample-0.0.1-SNAPSHOT.jar

```
**NOTE** *For illustration purposes the API response is written to console log..*
