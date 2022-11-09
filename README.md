# Person REST API Sample Application

## Pre-requirements:

- Java JDK 17.0.3.1 or higher
- Apache Maven 3.6.3 or higher
- Postman (For testing REST APIs)

## Application Deployment
This sample application uses an **internal H2 in-memory database** and there is no need to use an external RDBMS. 
 
For deploying the application run the following command:

`mvn clean compile spring-boot:run`

## How to Test

There is Postman collection file in `tests` directory (`PersonAPI.postman_collection.json`)

Import that file into Postman to test the APIs 

It is possible to test each request individually or to run all API tests as `Run collection` in Postman. 
There are assertions to ensure that HTTP status codes are as expected. 

