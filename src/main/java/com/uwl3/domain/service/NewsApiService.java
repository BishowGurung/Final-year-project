package com.uwl3.domain.service;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.uwl3.domain.cache.NewsCache;
import com.uwl3.domain.dao.HealthNews;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@EnableScheduling
@Configuration
public class NewsApiService {

    private RestTemplate restTemplate;
    private NewsCache newsCache;

    String apiKey ="7f1b2f400c524ffc8b43b22a8eae42ac";

    @Scheduled(cron = "0 1 * * * *")
    public void getNewsFromApi(){

        try {
            log.info("Getting news data from api");

            String url = "https://newsapi.org/v2/top-headlines?language=en";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("X-Api-Key",apiKey);

            httpHeaders.setBearerAuth("7f1b2f400c524ffc8b43b22a8eae42ac");

            HttpEntity<?> request = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,request,String.class);


            if(response.getStatusCode() == HttpStatus.OK) {
                healthNews(response.getBody());
            } else {
                log.info( "Error : " + response.getStatusCode().toString());
            }


        }catch (Exception e){
            log.info("Rest Template Exception");
        }
    }

    private void healthNews(String data){
        try {

            List<HealthNews> healthNewsList = new ArrayList<>();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(data).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("articles");

            jsonArray.forEach(u->{

                healthNewsList.add(
                        HealthNews.builder()
                                .author(getJsonValue("author",u.getAsJsonObject()))
                                .source(getJsonValue("source",u.getAsJsonObject()))
                                .content(getJsonValue("content",u.getAsJsonObject()))
                                .description(getJsonValue("description",u.getAsJsonObject()))
                                .title(getJsonValue("title",u.getAsJsonObject()))
                                .urlToImage(getJsonValue("urlToImage",u.getAsJsonObject()))
                                .publishedAt(getJsonValue("publishedAt",u.getAsJsonObject()))
                                .url(getJsonValue("url",u.getAsJsonObject()))
                        .build());
            });
            log.info(healthNewsList.size() +"");
            newsCache.setHealthNewsList(healthNewsList);
        }catch (Exception e){
            log.info("JSON error");
        }
    }

    private String getJsonValue(String value,JsonObject jsonObject){
        try {
            if(value.equals("source")){
                JsonObject jsonObject1 = jsonObject.getAsJsonObject("source").getAsJsonObject();
                return jsonObject1.get("name").getAsString();
            }
            return jsonObject.get(value).getAsString();
        }catch (Exception e){
            return  "";
        }
    }




}
