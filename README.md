
# Medication Delivery System


This project is a REST API service that allows clients to communicate with a fleet of drones that are capable of carrying and delivering medications.
## Table of Contents

 - Introduction
 - Requirements
    - Functional Requirements
    - Non-Functional Requirements
- Getting Started
    - Prerequisites
    - Building and Running the Application
    - Using the Application
- Assumptions
- Notes


## Introduction

In the field of transportation, a disruptive force is emerging that has the potential to revolutionize the way we think about delivering goods and services. This force is none other than the drone. Similar to how mobile phones allowed developing countries to skip traditional communication infrastructure, drones can leapfrog traditional transportation infrastructure. With the ability to deliver small items, such as medications, to locations with difficult access, drones can provide a solution to an urgent need. And with a fleet of 10 drones at our disposal, the possibilities are endless

# Requirements

# Functional Requirements

 - Prevent the drone from being loaded with more weight that it can carry
 - Prevent the drone from being in LOADING state if the battery level is below 25%
- Introduce a periodic task to check drones battery levels and create history/audit event log for this

### Non-Functional Requirements

 - Input/output data must be in JSON format
 - Your project must be buildable and runnable
- Required data must be preloaded in the database
- JUnit tests are optional but advisable
- Advice: Show us how you work through your commit history

# Getting Started

# Prerequisites

To build and run this application, you will need

 - Java 11
 - Spring Boot
 - H2 Database
 - Gradle
 - Docker


### Build and Run Instructions

 - Clone the repository to your local machine
 
			HTTPS : https://github.com/MasithPrasanga/drone-service.git
			SSH : git@github.com:MasithPrasanga/drone-service.git
			GitHub CLI : gh repo clone MasithPrasanga/drone-service
    
 - Open a terminal and navigate to the project directory
 - Build the application using Gradle

			./gradlew build
    
 - Create a Docker image
 
    		docker build -t drones-service .
    
 - Run the Docker container
 
    		docker run -p 8080:8080 drones-service

 - OpenAPI (Swagger) documentation link
 
    		http://localhost:8080/swagger-ui.html

# Database

The project uses an in-memory H2 database, which is preloaded with some sample data on startup. You can access the H2 console at

 - H2 console Link
 
    		http://localhost:8080/h2-console
    
 - H2 User Name
 
    		sa
 - Password
 
    		There is no password
    
### Assumptions

 - medications can only be loaded when a drone is at IDEL state
 - at a time drone is delivering medications to only one location
 - when a new drone is created it is at IDEL state
 - SerialNumber is a unique number for drone and each and every done can be identified by using serial number
 - drone battery level is reducing 1% per minute rate when the drone is not in the IDEL state
 - drone is charged only at IDEL state with 2% per minute rate
 - at the begining there are 10 drones
 
 
### Notes


- For drone input validation, the drone service validates for required fields, data type, length, format, and range to ensure that the drone data is valid and consistent
- For medication input validation, the service validates the medication data against required fields, data type, length, format, and range to ensure data consistency and avoid potential errors
 - The project includes a scheduled task that runs every 5 minutes to check the battery levels of all drones and create a history/audit event log
 - The project validates the weight limit of drones before loading medications and prevents drones from being loaded if the weight limit is exceeded
 - The project prevents drones from being in the LOADING state if their battery level is below 25%

 
 