# Use an official Node runtime as a parent image
FROM node:14-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install any dependencies
RUN npm install

# Bundle app source inside the Docker image
COPY . .

# Build the app
RUN npm run build

# Map port 3000 to the outside world (the port number should be the one your app uses)
EXPOSE 3000

# Define the command to run your app using CMD which defines your runtime
CMD [ "node", "src/core/Kernel.js" ]