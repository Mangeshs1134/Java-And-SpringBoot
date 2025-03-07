package com.myproject.myJournalProject.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myproject.myJournalProject.entity.ConfigJournalAppEntity;
import com.myproject.myJournalProject.repository.ConfigJournalAppRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String, String> APP_CACHE ;

    @Autowired
    public ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        APP_CACHE= new HashMap<>() ;
        List<ConfigJournalAppEntity> all;
        all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
