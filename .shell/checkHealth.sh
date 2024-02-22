#!/bin/bash

if [ $# -ne 2 ]; then
    echo "Usage: $0 <parameter_name> <endpoint>"
    exit 1
fi
if ! command -v jq &> /dev/null; then
    sudo apt-get update
    sudo apt-get install -y jq
fi
API_URL_SSM=$(aws ssm get-parameter --name "/poja-sarisary-std22099/$1/api/url")
if [ $? -ne 0 ]; then
    echo "Erreur lors de la récupération du paramètre depuis AWS SSM."
    exit 1
fi

API_URL=$(echo "$API_URL_SSM" | jq -r '.Parameter.Value')

if [ -z "$API_URL" ]; then
    echo "L'URL de l'API n'est pas définie."
    exit 1
fi

curl --fail "$API_URL$2"
