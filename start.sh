#!/bin/sh

mvn clean install

docker build -t mindata.es/superheroes:latest .

docker run  --name superheroes -dp 8080:8080  mindata.es/superheroes:latest
