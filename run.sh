#!/bin/sh

# mvn spring-boot:run -Dspring-boot.run.profiles=local -Dspring-boot.run.arguments="--custom.test1='test1Value' --url.datasource='localhost'"

docker build --build-arg JAR_FILE=restaurant-checker-0.0.1-SNAPSHOT.jar -f DockerSource/Dockerfile -t resto-i3 .
# docker build --no-cache --build-arg JAR_FILE=restaurant-checker-0.0.1-SNAPSHOT.jar -f DockerSource/Dockerfile -t resto-i3 .

docker compose -f DockerSource/docker-compose-local.yaml up


