package com.uwl3.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableScheduling
@Configuration
public class NewsApiService {

    @Autowired
    RestTemplate restTemplate;
    String apiKey ="7f1b2f400c524ffc8b43b22a8eae42ac";

    @Scheduled(cron = "0 * * * * *")
    public void getNewsFromApi(){

        try {
            log.info("Getting news data from api");

            String url = "https://newsapi.org/v2/top-headlines?language=en";
            // create headers
            HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
            // set `accept` header
            httpHeaders.set("X-Api-Key",apiKey);
            // set custom header
            httpHeaders.setBearerAuth("7f1b2f400c524ffc8b43b22a8eae42ac");



            // build the request
            HttpEntity request = new HttpEntity(httpHeaders);

            // use `exchange` method for HTTP call
            ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                log.info("Response : "+ response.getBody());
            } else {
                log.info( "Error : " + response.getStatusCode().toString());
            }
        }catch (Exception e){
            log.info("Rest Template Exception");
        }
    }
}
