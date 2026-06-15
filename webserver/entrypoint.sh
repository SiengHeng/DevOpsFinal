#!/bin/bash

# Start SSH service
service ssh start

# Start Nginx
service nginx start

# Clone the project if it doesn't exist
if [ ! -d "/app/DevOpsFinal" ]; then
    git clone https://github.com/SiengHeng/DevOpsFinal.git /app/DevOpsFinal
fi

cd /app/DevOpsFinal

# Build and run the application
# Assuming it uses Maven (mvnw) or Gradle (gradlew)
if [ -f "./mvnw" ]; then
    ./mvnw spring-boot:run
elif [ -f "./gradlew" ]; then
    ./gradlew bootRun
else
    # Fallback or generic run if built
    java -jar target/*.jar
fi
