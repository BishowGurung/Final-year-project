package com.uwl3.sensor;

public class RespiratoryReader {
    public String getRespiratoryRate(){
        return ((Math.random() * (18 - 12)) + 12) + "breaths per minute";
    }
}
