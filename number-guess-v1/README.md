# number-guess-v1

Rethinking the Spring Boot game, from the Currere [article](https://currere.co/knowledge/practical-spring-boot-microservices-1-refactoring-to-spring-boot).

Refactored starter, with tests. Does not use client.

Now generates a Game server-side, with original and average of games (can provide value; range etc).

## Setup

Clone example:

```bash
$ git clone https://github.com/gcavancer/practical-spring-boot-microservices
$ cd number-guess-v1
```

## Run Tests

Run from IDE, or command line:

```bash
$ mvn clean
$ mvn test
```