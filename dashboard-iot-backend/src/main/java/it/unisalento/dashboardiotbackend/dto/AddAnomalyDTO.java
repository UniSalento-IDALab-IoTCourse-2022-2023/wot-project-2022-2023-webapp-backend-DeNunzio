package it.unisalento.dashboardiotbackend.dto;

public class AddAnomalyDTO {
    String routeId;
    String type; // nome dell'anomalia (temperature, humidity, ... )
    String value;
    String time;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getType() {
        return type;
    }

    public void setTypee(String name) {
        this.type = name;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
