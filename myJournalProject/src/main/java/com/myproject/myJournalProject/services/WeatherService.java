package com.myproject.myJournalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myproject.myJournalProject.api_response.WeatherResponse;

@Service
public class WeatherService {
    private String apiAddrees = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    private static final String apiKey = "90e812b9afcf7e47415ec15d0613255f";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi = apiAddrees.replace("city", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
