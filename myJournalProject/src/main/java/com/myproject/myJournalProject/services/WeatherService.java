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

    @Autowired
    private RedisService redisService;
    

    public WeatherResponse getWeather(String city){

        // String finalApi = apiAddrees.replace("city", city).replace("API_KEY", apiKey);
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
        if (weatherResponse!=null){
            return weatherResponse;
        }else{
            String finalApi = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace("<API_KEY>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body!=null){
                redisService.set("weather_of_"+city, body, 5l);
            }
            return body;
        }

    }
}
