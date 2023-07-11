package it.unisalento.dashboardiotbackend.domain;

import java.util.ArrayList;
import java.util.List;

public class Anomaly {
    private String type;
    private List values;
    private List time;
    private int minValue;
    private int maxValue;

    public Anomaly(){
        this.values = new ArrayList();
        this.time = new ArrayList();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List getValue() {
        return values;
    }
    public void setValue(List values) {
        this.values = values;
    }
    public List getTime() {
        return time;
    }
    public void setTime(List time) {
        this.time = time;
    }
    public int getMinValue() {
        return minValue;
    }
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void addValue(String newValue, String newTime){
        this.values.add(newValue);
        this.time.add(newTime);
    }
}
