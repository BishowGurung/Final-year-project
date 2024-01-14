package com.uwl3.sensor;

import org.springframework.stereotype.Service;

@Service
public class BodyTemperatureReader {
    public String getBodyTemperature(){
        return  String.valueOf((((Math.random() * (37 - 36)) + 36))).substring(0,2) + "C";
    }

}
