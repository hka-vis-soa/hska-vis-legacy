#!/bin/sh

if [ -z "$DOCKER_ACCOUNT" ]; then
    echo "Variable DOCKER_ACCOUNT required. Use 'export DOCKER_ACCOUNT=my_account'"
    exit 1
fi

docker build --tag=hska-vis-legacywebshop -f ./docker/Dockerfile .
docker tag hska-vis-legacywebshop $DOCKER_ACCOUNT/hska-vis-legacywebshop:latest
docker push $DOCKER_ACCOUNT/hska-vis-legacywebshop

docker build --tag=hska-vis-web-shop-db-image -f ./docker/DockerfileMySQL .
docker tag hska-vis-web-shop-db-image $DOCKER_ACCOUNT/hska-vis-web-shop-db-image:latest
docker push $DOCKER_ACCOUNT/hska-vis-web-shop-db-image