package com.uwl3.sensor;

import org.springframework.stereotype.Service;

@Service
public class RespiratoryReader {
    public String getRespiratoryRate(){
        return String.valueOf(((Math.random() * (18 - 12)) + 12)).substring(0,2) + " breaths per minute";
    }
}
