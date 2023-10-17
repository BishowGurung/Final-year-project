package com.uwl3.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Slf4j
@EnableScheduling
@Configuration
public class NewsApiService {

    @Scheduled(cron = "0 * * * * *")
    public void getNewsFromApi(){
        log.info("Getting news data from api");
    }
}
