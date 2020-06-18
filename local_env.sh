#!/usr/bin/env bash

docker kill dynamodb
docker rm -v dynamodb

docker run --name dynamodb \
    --publish=8000:8000 \
    -d amazon/dynamodb-local:1.13.1
aws dynamodb create-table \
    --table-name game_connections \
    --key-schema AttributeName=gameId,KeyType=HASH AttributeName=connectionId,KeyType=RANGE \
    --attribute-definitions AttributeName=gameId,AttributeType=S AttributeName=connectionId,AttributeType=S \
    --billing-mode PAY_PER_REQUEST \
    --endpoint-url http://localhost:8000