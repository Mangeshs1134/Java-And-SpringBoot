package com.myproject.myJournalProject.api_response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WeatherResponse{
    // private Request request;
    // private Location location;
    private Current current;

@Getter
@Setter
public class Current{
    @JsonProperty("observation_time")
    private String observationTime;

    private int temperature;

    @JsonProperty("weather_code")
    private int weatherCode;

    @JsonProperty("weather_icons")
    private ArrayList<String> weatherIcons;

    @JsonProperty("weather_descriptions")
    private ArrayList<String> weatherDescriptions;

    @JsonProperty("wind_speed")
    private int windSpeed;

    @JsonProperty("wind_degree")
    private int windDegree;

    @JsonProperty("wind_dir")
    private String windDir;
    
    private int pressure;

    private int precip;
    
    private int humidity;

    private int cloudcover;
    
    private int feelslike;
    
    private int uv_index;
    
    private int visibility;
    
    @JsonProperty("is_day")
    private String isDay;
}

// public class Location{
//     private String name;
//     private String country;
//     private String region;
//     private String lat;
//     private String lon;
//     private String timezone_id;
//     private String localtime;
//     private int localtime_epoch;
//     private String utc_offset;
// }

// public class Request{
//     private String type;
//     private String query;
//     private String language;
//     private String unit;
// }


}