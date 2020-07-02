# number-guess-v4

Rethinking the Spring Boot game, from the Currere [article](https://currere.co/knowledge/practical-spring-boot-microservices-4-persistence).

Adding persistence with Spring Data JPA.

## Setup

Clone example:

```bash
$ git clone https://github.com/gcavancer/practical-spring-boot-microservices
$ cd number-guess-v4
```

## Run Tests

Run from IDE, or command line:

```bash
$ mvn clean
$ mvn test
```

## Run

Generate JAR to /target/number-guess-v4.jar:

```bash
$ mvn package
```

Run from IDE, or command line:

```bash
$ java -jar number-guess-v4.jar
```

## Endpoints

GET:  http://localhost:8080/number-guess-service/app/service/game

    Returns a new Game.

POST: http://localhost:8081/number-guess-service/app/results

    Header - Content-Type: application/json
    Body - {"guess":928,"guesses":1,"game":{"original":928,"average":12},"user":{"alias":"gc"}}