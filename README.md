## Introduction
 this dcoument outlines 
*Design
*Implementation
*Test
*Run
*Management & Monitoring (via Spring Boot Actuators)
*Documentation with Swagger
*Docker Containerization to be run with Container engines

 
## Prerequisites
 1) To run this application, *Java* and *Maven* software installations are required
 2) clone from GitHub URL
 
## Introduction
 This application can be tested for 2 use cases
1. OAuth client Application with FactSet generated keypair
2. OAuth client  application with usng own signing keys

## Implementation
this sample application is a multi module maven project
	1. *oauth2-client-parent      
	2. *oauth2-client-sdk
	3. *oauth2-client-sample                                        

It consists of one maven parent and 2 maven modules. maven parent is  to package multiple maven modules..
1. *oauth2-client-sdk* . this contains all the runtime libraries required to intiate the client credentials flow using client metadata file and fetch access token
2. *oauth2-client-sample* is a command line application which uses the SDK libraries to get access token and use that access token while sending a API request to protected resource

## Installation
  
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
{
 "token_endpoint": "https://auth-staging.factset.com/as/token.oauth2",
 "name": "test_client_1",
 "clientId": "a920deeb968442738095f81bfe7f4cb9",
  "pems": [
    {
      "keyId": "5d9f96560a294d538874976ffe60f257",
      "public": "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnilvVxPGaGSWejzD/QyE\n0gVcxn7kgsiCeVJyZheJ53HJWMgcCgaC2ywj7GDomsOiAYK2SrbVv7LzDmy16sl1\ntHXnX8OGPfcckgWqxE+mwaeneo4SY7a09Ae48fB4Faa5+9CcG2ppwOZo9tAO9n6G\nYusEj2ZsQROc2wnGIARUZ98bng9ZdnpaVMx1J2AsSy0W4uMMx7wt/dbVlXuiTGYW\nni/cztVvGC1VAsxDnN4nE5SFh6M86YYNC91e38U0UxBhDxuNncDV/AmfUyltRnpl\n8fOI8zJ5f1vNVZIbcWEkyyQelrev3CfCA23SUvFmgcPwCvVr+NOuUCfSTIw9p5TF\nCQIDAQAB\n-----END PUBLIC KEY-----\n",
      "private": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeKW9XE8ZoZJZ6\nPMP9DITSBVzGfuSCyIJ5UnJmF4nncclYyBwKBoLbLCPsYOiaw6IBgrZKttW/svMO\nbLXqyXW0dedfw4Y99xySBarET6bBp6d6jhJjtrT0B7jx8HgVprn70JwbamnA5mj2\n0A72foZi6wSPZmxBE5zbCcYgBFRn3xueD1l2elpUzHUnYCxLLRbi4wzHvC391tWV\ne6JMZhaeL9zO1W8YLVUCzEOc3icTlIWHozzphg0L3V7fxTRTEGEPG42dwNX8CZ9T\nKW1GemXx84jzMnl/W81VkhtxYSTLJB6Wt6/cJ8IDbdJS8WaBw/AK9Wv4065QJ9JM\njD2nlMUJAgMBAAECggEABqYRQyTWq17HIJ5bUtDq6HVHQCTncEQDkBwSKQ6GQo96\n+lt3Kki5yYLig7ZTMOZWch/Pj/Z9p9Ba/Vk+kH8ljo+CSXlLtGEl91F7q0L57Jwi\nozlDtqNgmf2VHb6RHb/jg51yYoinp29ILE/0wRHyjkf46hpRJK8xYdfYV1I3RiJ+\nhV+zhBbe5yAqaKdskC8G6rwtxrSxkX03lj+pkheU8arkMadQDF49wZ3++11TQT0b\nKT6aoiWkN2ysftpdOpXkW3sCQSLCn7JP1pri/u23LMzieexYb0SrjCdnh6u8OPe/\nTaiO0D3nfxLeKbJtuu8LAC07u9TB7FbS/GjIkWS2zQKBgQD9V05Z8v7GoZ6mwqYq\nBQQPycQnWwFLPOi8lltvBLr7fSsOhFS/e45CFn4rvgJvCqQr6swkS72kgpfXrAua\n3MpimKZJrfL9ktBJY8Yi6FwXiAwkHTCmyf/wPl1yn+0e4iJdWD6FjpeG/4ooVTLI\n2i83b+rBlNbK1aJuv+XCKUm4ywKBgQCf0mUbyAXwQCKbHgdMvbIr0wxzO/7JRqoq\nJNQ0+bFUEST4PbmswskQJghYmog3qEQ4r4oTP//BjKCBk+mKInJStVULzGZCfEdo\n/ejDdk4O/jOoOOSE9/liK9a+pdkh0Hpvb1z3TrRmyJI+Dmpji3JYmMovonqKuqki\nNB37bjEC+wKBgQC97uyxBzr31EfLondNXYFUDOLg1pu7uqiKaveV3igJUCgttAyN\nmqZm6dIiHUxZz9KRknEGbTp60eXhLf1tV0bscKGK770TWzSJMSJlHWudwMPJd5D4\nGs2lYjxRKZFu6/7zmKsXGnaEIaWE44s1hk6L834/L8VxQIWNHpW04ZuBzQKBgFWY\nvYYvjt6put8RKh+zyWUnV5ewHjl2m1SO/QiSYqL/u6kohfU9Lap0dCvgN+x3NnKV\nsYG12Si0RJKhwYa2BDy/2ZjEFFCvdx+IPc3R4uSsyMXkEqAUn4AwsobRFIXDvo9I\nOaZKhNw3t/t8hQZjfQ5uZns0riT26/bhqt0qmx1ZAoGARGIK+eA69eIjEIaVFkZM\neXKz7wsjcPuCqbr/ELjdelP/djssMJkfXYo1JTS/6Cb1wR6B+5usU+nZrW1Eq6gg\n84W7Wnc/kbayMtNVhoqDuCOs4aL2pVvQg2HCFq+uTYsRXRSD59oOzvRKOX3jDx46\ncK7n4cyrYZQlabX3pccJ0cs=\n-----END PRIVATE KEY-----\n"
    }
  ]
}
```
 5. In case if client application in dev portal is created wih 'use own signing keys' option, the JSON should have an additional element 
 *private_key_pem_path*. Its value should contain the full file path to the private key location.
 
 **NOTE** It can be seen in the below JSON, that the *private* value is null, because only *public* is used during client registraion in dev portal. Private key is essential to sign token requests
 
 ```json
{
 "token_endpoint": "https://auth-staging.factset.com/as/token.oauth2",
 "name": "test_own_key_client_1",
 "clientId": "a920deeb968442738095f81bfe7f4cb9",
  "pems": [
    {
      "keyId": "5d9f96560a294d538874976ffe60f257",
      "public": "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnilvVxPGaGSWejzD/QyE\n0gVcxn7kgsiCeVJyZheJ53HJWMgcCgaC2ywj7GDomsOiAYK2SrbVv7LzDmy16sl1\ntHXnX8OGPfcckgWqxE+mwaeneo4SY7a09Ae48fB4Faa5+9CcG2ppwOZo9tAO9n6G\nYusEj2ZsQROc2wnGIARUZ98bng9ZdnpaVMx1J2AsSy0W4uMMx7wt/dbVlXuiTGYW\nni/cztVvGC1VAsxDnN4nE5SFh6M86YYNC91e38U0UxBhDxuNncDV/AmfUyltRnpl\n8fOI8zJ5f1vNVZIbcWEkyyQelrev3CfCA23SUvFmgcPwCvVr+NOuUCfSTIw9p5TF\nCQIDAQAB\n-----END PUBLIC KEY-----\n",
	  "private": null
    }
  ],
   "private_key_pem_path": "C:/Downloads/oc_client_1_private.pem"
}
```

## Test Run
To run the sample application, run the executable jar located at *./oauth2-client-sample/target/oauth-factset-client-0.0.1-SNAPSHOT.jar*

1. 
```command line
	java -jar .\oauth2-client-sample\target\oauth2-client-sample-0.0.1-SNAPSHOT.jar https://oauth-mirror.staging-cauth.factset.com

```
**NOTE** *https://oauth-mirror.staging-cauth.factset.com* is test API url used for testing purposes
**NOTE For Developers** sending HTTPS calls via from client application requires, FactSet's SSL certificate to be imported into your local JRE's truststore. 

2. In case if environment variable *oauth2.client.file.path* is not set as explained above, the file path can be passed as a command line argument as shown below

```command line
	java -jar .\oauth2-client-sample\target\oauth2-client-sample-0.0.1-SNAPSHOT.jar  C:\Downloads\client.json  https://oauth-mirror.staging-cauth.factset.com

```
 For illustration purposes the API response is written to console log..
