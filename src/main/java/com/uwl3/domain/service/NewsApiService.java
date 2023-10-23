package com.uwl3.domain.service;


import com.google.gson.JsonArray;
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

    String apiKey ="7f1b2f400c524ffc8b43b22a8eae42ac";

    @Scheduled(cron = "0 * * * * *")
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
            jsonArray.forEach(jsonElement -> {
                JsonObject jsonObject2 = jsonParser.parse(jsonElement.getAsString()).getAsJsonObject();
                HealthNews healthNews = HealthNews.builder()
                        .author(jsonObject2.get("author").getAsString())
                        .content(jsonObject2.get("").getAsString())
                        .description(jsonObject2.get("description").getAsString())
                        .url(jsonObject2.get("url").getAsString())
                        .source(jsonObject2.get("source").getAsString())
                        .urlToImage(jsonObject2.get("urlToImage").getAsString())
                        .publishedAt(jsonObject2.get("publishedAt").getAsString())
                        .title(jsonObject2.get("title").getAsString()).build();
            });
            log.info(healthNewsList.size() +"");
        }catch (Exception e){
            log.info("JSON error");
        }
    }
}
