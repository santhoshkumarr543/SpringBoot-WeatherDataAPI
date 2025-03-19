package com.example.weather_data_api.service;

import com.example.weather_data_api.dto.GeoCodingResponseDTO;
import com.example.weather_data_api.dto.WeatherApiResponseDTO;
import com.example.weather_data_api.dto.WeatherResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class WeatherService {

    //Webclient used to integrate the external data into the spring boo application in the form of API
    private final WebClient weatherWebClient;
    private final WebClient geoWebClient;

    //constructor injection
    public WeatherService(WebClient weatherWebClient, WebClient geoWebClient) {
        this.weatherWebClient = weatherWebClient;
        this.geoWebClient = geoWebClient;
    }

    //step 1: convert the location name into latitude & longitude
    public GeoCodingResponseDTO.Location getCoordinates(String location){
        GeoCodingResponseDTO response = geoWebClient.get().uri(uriBuilder -> uriBuilder.path("/v1/search")
                .queryParam("name", location)
                .queryParam("count", 1)
                .queryParam("language", "en").build())
                .retrieve().bodyToMono(GeoCodingResponseDTO.class).block();

        return (response != null && !response.getResults().isEmpty()) ? response.getResults().get(0) : null;
    }

    //step 2:get weather data using latitude and longitude
    public WeatherResponseDTO getWeather(String location){

        GeoCodingResponseDTO.Location coordinates = getCoordinates(location);
        if(coordinates == null) return null;

        //https://api.open-meteo.com/v1/forecast?latitude=13.08784&longitude=80.27847&current_weather=true
        WeatherApiResponseDTO response = weatherWebClient.get().uri(uriBuilder -> uriBuilder.path("/v1/forecast")
                .queryParam("latitude",coordinates.getLatitude())
                .queryParam("longitude" , coordinates.getLongitude())
                .queryParam("current_weather" , "true").build())
                .retrieve().bodyToMono(WeatherApiResponseDTO.class).block();

        WeatherResponseDTO weatherResponse = response.getCurrentWeather();

        //add weather Description and emoji
        weatherResponse.setWeatherDescription(getWeatherDescription(weatherResponse.getWeatherCode()));
        weatherResponse.setWeatherIcon(getWeatherIcon(weatherResponse.getWeatherCode()));

        return weatherResponse;
    }

    //map weather code to description
    private String getWeatherDescription(int code){
        Map<Integer , String> weatherDescription = Map.ofEntries(
                Map.entry(0, "Clear sky"),
                Map.entry(1, "Mainly clear"),
                Map.entry(2, "Partly cloudy"),
                Map.entry(3, "Overcast"),
                Map.entry(45, "Fog"),
                Map.entry(48, "Depositing rime fog"),
                Map.entry(51, "Light drizzle"),
                Map.entry(53, "Moderate drizzle"),
                Map.entry(55, "Heavy drizzle"),
                Map.entry(56, "Freezing drizzle"),
                Map.entry(57, "Heavy freezing drizzle"),
                Map.entry(61, "Light rain"),
                Map.entry(63, "Moderate rain"),
                Map.entry(65, "Heavy rain"),
                Map.entry(80, "Rain showers"),
                Map.entry(81, "Heavy rain showers"),
                Map.entry(82, "Violent rain showers"),
                Map.entry(95, "Thunderstorm"),
                Map.entry(96, "Thunderstorm with hail"),
                Map.entry(99, "Severe thunderstorm with hail"));

        return weatherDescription.getOrDefault(code , "Unknown Weather..!");
    }

    //map weather code into emoji icon
    private String getWeatherIcon(int code){
        Map<Integer , String> weatherIcons = Map.ofEntries(
                Map.entry(0, "☀"),  // Clear sky
                Map.entry(1, "🌤"),  // Mainly clear
                Map.entry(2, "⛅"),   // Partly cloudy
                Map.entry(3, "☁"),  // Overcast
                Map.entry(45, "🌫"), // Fog
                Map.entry(48, "🌁"),  // Depositing rime fog
                Map.entry(51, "🌦"), // Light drizzle
                Map.entry(53, "🌧"), // Moderate drizzle
                Map.entry(55, "🌧"), // Heavy drizzle
                Map.entry(56, "🌨"), // Freezing drizzle
                Map.entry(57, "❄"),  // Heavy freezing drizzle
                Map.entry(61, "🌧"), // Light rain
                Map.entry(63, "🌧"), // Moderate rain
                Map.entry(65, "🌧"), // Heavy rain
                Map.entry(80, "🌦"), // Rain showers
                Map.entry(81, "🌧"), // Heavy rain showers
                Map.entry(82, "⛈"), // Violent rain showers
                Map.entry(95, "🌩"), // Thunderstorm
                Map.entry(96, "⛈"), // Thunderstorm with hail
                Map.entry(99, "⛈")); // Severe thunderstorm with hail
        return weatherIcons.getOrDefault(code, "❓"); // Default icon if code not found
    }
}
