# Signal-Sense: Volunteer Matching Platform (CLI Edition)

## Overview of the project
Signal-Sense is an AI-powered Command Line Interface (CLI) application that connects volunteers with local community needs. Organizations can post volunteering opportunities, while volunteers can create profiles with their skills and interests. A custom-built Java recommendation engine uses TF-IDF and Cosine Similarity to automatically match the best volunteers to the right opportunities.

## Features
- **Dual Profile System** — Separate registration and login workflows for Volunteers and Organizations.
- **Opportunity Management** — Organizations can post and track their opportunities.
- **AI Recommendation Engine** — A content-based filtering algorithm built natively in Java that matches volunteers to opportunities based on skills and categories.
- **Interactive CLI Interface** — Easy-to-navigate terminal menus for interacting with the platform.

## Technologies/Tools Used
- **Language**: Java 17
- **Framework**: Spring Boot (Core, Data JPA)
- **Database**: H2 In-Memory Database (for seamless execution without setup)
- **Build Tool**: Maven

## Steps to install & run the project

### Prerequisites
- Java 17+ installed
- Maven installed

### Run Instructions
1. Clone this repository to your local machine.
2. Navigate to the `backend/` directory in your terminal:
   ```bash
   cd backend
   ```
3. Run the Spring Boot application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. The application will start in your terminal and present the interactive Signal-Sense Main Menu.

## Instructions for testing
1. Start the application.
2. Select `3` to Register as an Organization and follow the prompts.
3. Select `4` to Login as the Organization you just created.
4. Select `1` to Post a new Opportunity (add skills like "teaching" or "first aid").
5. Log out and select `1` to Register as a Volunteer (add matching skills).
6. Log in as the Volunteer and select `1` to View AI Recommendations. You should see the opportunity ranked based on similarity!
