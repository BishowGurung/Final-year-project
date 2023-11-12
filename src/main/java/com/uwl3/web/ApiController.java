package com.uwl3.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uwl3.domain.cache.NewsCache;
import com.uwl3.domain.dao.HealthNews;
import com.uwl3.domain.service.NhsApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
@Slf4j
public class ApiController {

    @Autowired
    private NewsCache newsCache;

    @Autowired
    private NhsApiService nhsApiService;
    @GetMapping(value = "/api/healthnews")
    public String getHealthNews(){
        if (newsCache.getHealthNewsList().isEmpty()){
            Map<String,String> healthNews = new HashMap<>();
            healthNews.put("source","Hindustan Times");
            healthNews.put("title","LIVE: WHO receives estimates of some 1,000 bodies buried under rubble in Gaza - Hindustan Times");
            healthNews.put("Description","Israel-Hamas war LIVE updates: Follow all the latest updates on the war in the Middle-Eastern region.");
            healthNews.put("PublishedAt","2023-10-27T12:26:52Z");
            healthNews.put("context","Israel-Hamas war LIVE updates: The Israeli military has been carrying out brief raids across the Gaza border to prepare for battlefield ahead of an expected large-scale ground incursion into the the … [+12335 chars]");
            healthNews.put("urlToImage","https://www.hindustantimes.com/ht-img/img/2023/10/27/1600x900/TOPSHOT-PALESTINIAN-ISRAEL-CONFLICT-0_1698387971549_1698387988791.jpg");
            healthNews.put("url","https://www.hindustantimes.com/world-news/israelhamas-war-live-updates-october-27-palestine-gaza-war-benjamin-netanyahu-israel-attack-101698366686195.html");
            healthNews.put("author","HT News Desk");

            Map<String,String> healthNews2 = new HashMap<>();
            healthNews2.put("source","Hindustan Times");
            healthNews2.put("title","LIVE: WHO receives estimates of some 1,000 bodies buried under rubble in Gaza - Hindustan Times");
            healthNews2.put("Description","Israel-Hamas war LIVE updates: Follow all the latest updates on the war in the Middle-Eastern region.");
            healthNews2.put("PublishedAt","2023-10-27T12:26:52Z");
            healthNews2.put("context","Israel-Hamas war LIVE updates: The Israeli military has been carrying out brief raids across the Gaza border to prepare for battlefield ahead of an expected large-scale ground incursion into the the … [+12335 chars]");
            healthNews2.put("urlToImage","https://www.hindustantimes.com/ht-img/img/2023/10/27/1600x900/TOPSHOT-PALESTINIAN-ISRAEL-CONFLICT-0_1698387971549_1698387988791.jpg");
            healthNews2.put("url","https://www.hindustantimes.com/world-news/israelhamas-war-live-updates-october-27-palestine-gaza-war-benjamin-netanyahu-israel-attack-101698366686195.html");
            healthNews2.put("author","HT News Desk");

            JsonArray jsonArray = new JsonArray();
            jsonArray.add(healthNews.toString());
            jsonArray.add(healthNews2.toString());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("news",jsonArray);

            return jsonObject.toString();
        }else {
            List<HealthNews> healthNewsList = newsCache.getHealthNewsList();

            JsonArray jsonArray = new JsonArray();

            healthNewsList.forEach(healthNews -> {

                Map<String,String> healthNewsMap = new HashMap<>();
                healthNewsMap.put("source",healthNews.getSource());
                healthNewsMap.put("title",healthNews.getTitle());
                healthNewsMap.put("Description",healthNews.getDescription());
                healthNewsMap.put("PublishedAt",healthNews.getPublishedAt());
                healthNewsMap.put("context",healthNews.getContent());
                healthNewsMap.put("urlToImage",healthNews.getUrlToImage().equals("")?"/img/mainlogo.png":healthNews.getUrlToImage());
                healthNewsMap.put("url",healthNews.getUrl());
                healthNewsMap.put("author",healthNews.getAuthor());

                jsonArray.add(healthNewsMap.toString());
            });

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("news",jsonArray);

            return jsonObject.toString();
        }
    }
    @GetMapping(value = "/api/")
    public String getHealthNews(String query){
        return nhsApiService.getHealthInfo(query);
    }

}
