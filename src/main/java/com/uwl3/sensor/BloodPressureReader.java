package com.uwl3.sensor;

import org.springframework.stereotype.Service;

@Service
public class BloodPressureReader {

    public String getBloodPressure(){
        return String.valueOf(((Math.random() * (90 - 65)) + 65)).substring(0,2) + "mm - " + String.valueOf(((Math.random() * (120 - 100)) + 100)).substring(0,2)+"mm";
    }
}
