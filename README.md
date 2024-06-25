# Financiera Devsu

## Description
Project built in Spring Boot with Bank operations

## Requirements
- Java 17
- Git
- Docker (Optional)

## Installation
Clone the repository to your local machine:
```console
   git clone https://github.com/ChristianRam/financiera-devsu
   ```

## Local execution
1. Navigate to the project directory.
2. Compile each microservice by running:
```console
   ./mvnw clean install
   ```
3. Start application by running:
```console
   ./mvnw spring-boot:run
   ```

## Docker execution
1. Navigate to the root project directory, where docker-compose file is located.
3. Start application by running:
```console
   docker-compose up --build