#!/bin/bash

git pull

mvn clean
mvn package

docker-compose up --build -d