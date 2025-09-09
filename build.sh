#!/bin/bash

# Build the project
mvn clean install || { echo "Build failed"; exit 1; }

# Navigate to draw module and run the application
cd draw || { echo "Failed to change directory"; exit 1; }
mvn exec:java || { echo "Failed to run application"; exit 1; }
cd ..

# Pause before exit
read -p "Press Enter to continue..."