# 🌦️ Fetch the Realtime Weather Details – Spring Boot Project  

## 📌 Overview  
This **Spring Boot** project fetches real-time weather details based on a user-provided city name. It integrates with external APIs to first retrieve the **latitude and longitude** of the city and then fetch the corresponding **weather data**. The response is structured in JSON format and can be tested using **Postman**.  

## 🏗️ Project Structure  
weather-data-api
│── src
│   ├── main
│   │   ├── java/com.example.weather_data_api
│   │   │   ├── config/
│   │   │   │   ├── WebClientConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── WeatherController.java
│   │   │   ├── dto/
│   │   │   │   ├── GeoCodingResponseDTO.java
│   │   │   │   ├── WeatherApiResponseDTO.java
│   │   │   │   ├── WeatherResponseDTO.java
│   │   │   ├── service/
│   │   │   │   ├── WeatherService.java
│   │   │   ├── WeatherDataApiApplication.java
│── target/
│── pom.xml
│── .gitignore
│── HELP.md


## 🔹 Features  
✅ Takes **city name** as input  
✅ Retrieves **latitude & longitude** using an external API  
✅ Fetches **real-time weather data** based on coordinates  
✅ Displays weather details in **JSON format**  
✅ Uses **Spring Boot REST API** for smooth integration  

## 🛠️ Technologies Used  
- **Spring Boot** – Backend framework  
- **REST API** – External API integration  
- **WebClient** – Handling API requests  
- **Postman** – API testing  
- **JSON** – Parsing API responses  
- **Maven** – Dependency management  

## ⚙️ How It Works  
1️⃣ The user enters a **city name**.  
2️⃣ The **GeoCoding API** retrieves its **latitude and longitude**.  
3️⃣ The **Weather API** fetches real-time weather details using the coordinates.  
4️⃣ The retrieved weather data (temperature, humidity, etc.) is displayed in **Postman**.  

## 🏗️ Project Modules  
### 📌 `config/`  
- **WebClientConfig.java**: Configures **WebClient** to handle external API calls.  

### 📌 `controller/`  
- **WeatherController.java**: Handles API requests and sends responses to the client.  

### 📌 `dto/` (Data Transfer Objects)  
- **GeoCodingResponseDTO.java**: Stores city latitude & longitude.  
- **WeatherApiResponseDTO.java**: Stores weather API response.  
- **WeatherResponseDTO.java**: Stores the processed weather data.  

### 📌 `service/`  
- **WeatherService.java**: Contains business logic for fetching weather details.  

## 🏗️ Setup and Installation  
### Prerequisites  
- Java 17+  
- Maven  
- Postman (for testing API responses)  

📌 Learning Outcomes
✅ Working with Spring Boot REST APIs
✅ Fetching & processing real-time external API data
✅ Using WebClient for API calls
✅ Structuring DTOs for better API response management
✅ Testing APIs with Postman
