package com.example.weather_data_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //ignore the unused fields
public class WeatherResponseDTO {

    @JsonProperty("temperature")
    private String temprature;

    @JsonProperty("windspeed")
    private double windSpeed;

    @JsonProperty("weathercode")
    private int weatherCode;

    @JsonProperty("time")
    private String time;

    private String weatherDescription;
    private String weatherIcon;
}
