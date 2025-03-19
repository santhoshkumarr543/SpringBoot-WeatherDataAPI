package com.example.weather_data_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //to get weather data
    @Bean
    public WebClient weatherWebClient(WebClient.Builder builder){
        return builder.baseUrl("https://api.open-meteo.com").build();
    }

    //to get latitude and longitude
    @Bean
    public WebClient geoWebClient(WebClient.Builder builder){
        return builder.baseUrl("https://geocoding-api.open-meteo.com").build();
    }
}
