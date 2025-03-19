package com.example.weather_data_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeoCodingResponseDTO {

    private List<Location> results;

    @Data
    public static class Location {
        private double latitude;
        private double longitude;
    }
}
