#!/usr/bin/env bash

echo "Generating package"
mvn clean install -DskipTests

echo "Building containers"
docker compose up --build


