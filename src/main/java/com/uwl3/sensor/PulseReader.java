package com.uwl3.sensor;

public class PulseReader {

    public String getPulse(){
        return ((Math.random() * (100 - 80)) + 80) + "beats per minute";
    }

}
