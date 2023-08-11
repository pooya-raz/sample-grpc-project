# Sample Spring Boot gRPC Project

This is a sample Spring Boot gRPC project that uses
LogNet's [grpc-spring-boot-starter](https://github.com/LogNet/grpc-spring-boot-starter/) module. It implements both a
client and a server. The client tests will run against the live server.

## Running the server

Similar to any other Spring Boot application, you can run the server by executing the following command:

``` ./gradlew bootRun ```

## Running the client tests

1. Start the server by executing the following command:

   ``` ./gradlew bootRun ```
2. Run the client tests by executing the following command:

   ``` ./gradlew test ```
