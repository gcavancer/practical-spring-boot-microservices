# number-guess-v2

Rethinking the Spring Boot game, from the Currere [article](https://currere.co/knowledge/practical-spring-boot-microservices-2-controller-and-tests).

Added controller, with tests.

## Setup

Clone example:

```bash
$ git clone https://github.com/gcavancer/practical-spring-boot-microservices
$ cd number-guess-v2
```

## Run Tests

Run from IDE, or command line:

```bash
$ mvn clean
$ mvn test
```

## Run

Generate JAR to /target/number-guess-v2.jar:

```bash
$ mvn package
```

Run from IDE, or command line:

```bash
$ java -jar number-guess-v2.jar
```

## Endpoints

GET:  http://localhost:8080/number-guess-service/app/service/game

    Returns a new Game.

POST: http://localhost:8080/number-guess-service/app/service

    Header - Content-Type: application/json
    Body - { "guesses":10 }