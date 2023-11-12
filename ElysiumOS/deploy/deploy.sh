#!/bin/bash

# Deployment script for Elysium OS

# Exit on any error
set -e

# Define the path to the Dockerfile and Kubernetes configurations
DOCKERFILE_PATH="ElysiumOS/deploy/Dockerfile"
KUBERNETES_CONFIG_PATH="ElysiumOS/kubernetes"

# Build the Docker image
echo "Building Elysium OS Docker image..."
docker build -t elysiumos:latest -f $DOCKERFILE_PATH .

# Push the image to the registry
echo "Pushing image to Docker registry..."
docker push elysiumos:latest

# Apply Kubernetes configurations
echo "Applying Kubernetes configurations..."
kubectl apply -f $KUBERNETES_CONFIG_PATH

# Check rollout status
echo "Checking rollout status..."
kubectl rollout status deployment/elysiumos

# Deployment complete
echo "Elysium OS deployment complete."