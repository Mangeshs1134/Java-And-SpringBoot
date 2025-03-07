package com.myproject.myJournalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myproject.myJournalProject.api_response.WeatherResponse;
import com.myproject.myJournalProject.cache.AppCache;
import com.myproject.myJournalProject.constants.Placeholders;

@Service
public class WeatherService {
    private String apiAddrees = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Value("${weather.api.key}")
    private String apiKey ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;
    

    public WeatherResponse getWeather(String city){

        // String finalApi = apiAddrees.replace("city", city).replace("API_KEY", apiKey);

        System.out.println("line w1 passed");
        System.out.println(appCache.APP_CACHE);
        System.out.println(appCache.APP_CACHE.get("apiKey"));
        
        String finalApi = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace("<API_KEY>", apiKey);

        System.out.println("line w2 passed");
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        System.out.println("line w3 passed");
        WeatherResponse body = response.getBody();
        System.out.println("line w4 passed");
        return body;
    }
}
