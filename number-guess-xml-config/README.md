# number-guess-xml-config

Original JAX-RS project with XML configuration, from the Currere [article](https://currere.co/knowledge/practical-spring-boot-microservices).

## Setup

Clone example:

```bash
$ git clone https://github.com/gcavancer/practical-spring-boot-microservices
$ cd number-guess-xml-config
```
Generate WAR to /target/number-guess-service.war:

```bash
$ mvn package
```
## Run

Hot drop WAR to [Tomcat]/webapps/

## Endpoints

GET:  http://localhost:8080/number-guess-service/app/service/average

    Returns 30 on first call, due to dummy data

POST: http://localhost:8080/number-guess-service/app/service

    Header - Content-Type: application/json
    Body - { "guesses":10 }