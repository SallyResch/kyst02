package com.sillysally.kyst02.api;

import java.util.ArrayList;

public class Hourly {
    private ArrayList<String> time;
    private ArrayList<Double> temperature_2m;
    private ArrayList<Double> snowfall;

    public ArrayList<String> getTime() {
        return time;
    }

    public void setTime(ArrayList<String> time) {
        this.time = time;
    }

    public ArrayList<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(ArrayList<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public ArrayList<Double> getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(ArrayList<Double> snowfall) {
        this.snowfall = snowfall;
    }

    @Override
    public String toString() {
        return "Hourly{" +
                "time=" + time +
                ", temperature_2m=" + temperature_2m +
                ", snowfall=" + snowfall +
                '}';
    }
}
