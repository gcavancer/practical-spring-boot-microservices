# number-guess-boot

Initial refactor to Spring Boot project, from the Currere [article](https://currere.co/knowledge/practical-spring-boot-microservices-1-refactoring-to-spring-boot).

## Setup

Clone example:

```bash
$ git clone https://github.com/gcavancer/practical-spring-boot-microservices
$ cd number-guess-boot
```
Generate JAR to /target/number-guess-boot.jar:

```bash
$ mvn package
```
## Run

Run from IDE, or command line:

```bash
$ java -jar number-guess-boot.jar
```
## Endpoints

GET:  http://localhost:8080/number-guess-service/app/service/average

    Returns 30 on first call, due to dummy data

POST: http://localhost:8080/number-guess-service/app/service

    Header - Content-Type: application/json
    Body - { "guesses":10 }