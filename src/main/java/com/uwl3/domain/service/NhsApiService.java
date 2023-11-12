package com.uwl3.domain.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@EnableScheduling
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
                return filterContent(response.getBody());
            } else {
                log.info( "Error : " + response.getStatusCode().toString());
            }
        }catch (Exception e){
            log.info("Rest Template Exception");
        }
        return "";
    }

    private String filterContent(String response){
        StringBuilder stringBuilder = new StringBuilder();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();

        stringBuilder.append("<h2>"+ jsonObject.get("name").getAsString() +"</h2>");
        stringBuilder.append(jsonObject.get("description").getAsString());
        stringBuilder.append(filerBody(response));


        return stringBuilder.toString();
    }

    private String filerBody(String response){

        try {
            StringBuilder stringBuilder = new StringBuilder();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("hasPart");

            jsonArray.forEach(u->{

                stringBuilder.append("<h3>"+ u.getAsJsonObject().get("headline").getAsString() +"</h3>");
                stringBuilder.append("<p>"+ u.getAsJsonObject().get("description").getAsString() +"</p>");
                JsonArray childJsonArray = u.getAsJsonObject().getAsJsonArray("hasPart");

                childJsonArray.forEach(data->{
                    try {
                        JsonObject childJsonObject = data.getAsJsonObject();
                        stringBuilder.append(childJsonObject.get("text").getAsString());
                    }catch (Exception e){}
                });


            });

            return stringBuilder.toString();
        }catch (Exception e){
            return "";
        }
    }
}
