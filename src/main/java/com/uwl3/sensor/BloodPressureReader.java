package com.uwl3.sensor;

public class BloodPressureReader {
    public String getBodyTemperature(){
        return  (((Math.random() * (37 - 36)) + 36)) + "C";
    }

}
