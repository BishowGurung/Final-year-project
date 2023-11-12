package com.uwl3.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@EnableScheduling
@Configuration
public class NhsApiService {

    private RestTemplate restTemplate;

    final String subscriptionKey = "77356b4a61e54d1eae23b98b4310d973";

    public String getHealthInfo(String query){
        try {
            log.info("Starting nhs api");
            String url = "https://api.nhs.uk/conditions/";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("subscription-key",subscriptionKey);


            HttpEntity<?> request = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> response = restTemplate.exchange(url + query.toLowerCase(), HttpMethod.GET,request,String.class);


            if(response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                log.info( "Error : " + response.getStatusCode().toString());
            }
        }catch (Exception e){
            log.info("Rest Template Exception");
        }
        return "";
    }
}
