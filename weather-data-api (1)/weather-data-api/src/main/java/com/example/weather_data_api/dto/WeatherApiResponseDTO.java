package com.example.weather_data_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponseDTO {

    @JsonProperty("current_weather")
    private WeatherResponseDTO currentWeather;

}
