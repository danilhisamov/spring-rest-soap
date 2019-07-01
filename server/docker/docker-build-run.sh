#!/usr/bin/env bash
docker stop docker_jm-app_1
docker rm docker_jm-app_1
docker image rm dkh-pr1:latest
docker build -t dkh-pr1:latest .
docker-compose up -d