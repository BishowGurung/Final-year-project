package com.uwl3.sensor;

public class BloodTemperatureReader {

    public String getBloodPressure(){
        return ((Math.random() * (90 - 65)) + 65) + "mm - " + ((Math.random() * (120 - 100)) + 100)+"mm";
    }

}
