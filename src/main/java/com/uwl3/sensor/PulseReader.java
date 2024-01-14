package com.uwl3.sensor;

import org.springframework.stereotype.Service;

@Service
public class PulseReader {

    public String getPulse(){
        return String.valueOf(((Math.random() * (100 - 80)) + 80)).substring(0,2) + " beats per minute";
    }

}
