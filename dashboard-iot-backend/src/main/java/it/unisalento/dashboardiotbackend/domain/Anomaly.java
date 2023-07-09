package it.unisalento.dashboardiotbackend.domain;

public class Anomaly {
    private String type;
    private String[] values;
    private String[] time;
    private int minValue;
    private int maxValue;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String[] getValue() {
        return values;
    }
    public void setValue(String[] values) {
        this.values = values;
    }
    public String[] getTime() {
        return time;
    }
    public void setTime(String[] time) {
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
}
