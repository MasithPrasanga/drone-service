
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

 - Medications can only be loaded onto a drone while it is in the idle state
 - The drone is capable of delivering medications to only one location at a time
 - When a new drone is created, it starts in the IDLE state
 - The Serial Number is a distinctive identifier for each drone, enabling the identification of each individual unit
 - The rate of reduction of drone battery level is 1% per minute when the drone is in a non-IDLE state
 - The drone can only be charged when it is in the IDEL state and the charging rate is 2% per minute
 - After being loaded with medications, the drone commences the delivery process within a period of 5 minutes
 - The drone is programmed to deliver medications only to locations that can be reached within a 30-minute timeframe
 - After delivering medications to the designated location, the drone is scheduled to start returning within 5 minutes
 - When drone starts returning, the drone is scheduled to return within 30 minutes
 - Once a drone returns, it will transition to the IDLE state within 5 minutes
 
 
### Notes


- For drone input validation, the drone service validates for required fields, data type, length, format, and range to ensure that the drone data is valid and consistent
- For medication input validation, the service validates the medication data against required fields, data type, length, format, and range to ensure data consistency and avoid potential errors
 - The project includes a scheduled task that runs every 5 minutes to check the battery levels of all drones and create a history/audit event log
 - The project validates the weight limit of drones before loading medications and prevents drones from being loaded if the weight limit is exceeded
 - The project prevents drones from being in the LOADING state if their battery level is below 25%

 
 