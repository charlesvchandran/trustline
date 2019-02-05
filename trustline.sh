#!/bin/bash

mvn clean
mvn install
docker build -f Dockerfile -t trustline .
docker run -p 8080:8080 -t trustline

### maven build and run

mvn clean package docker:build
docker images -a
docker run -p 8080:8080 -t trustline

#to kill the docker container - server started to stop
docker container ls -a
docker container stop/kill <container id>

#to package, push and deploy
mvn deploy