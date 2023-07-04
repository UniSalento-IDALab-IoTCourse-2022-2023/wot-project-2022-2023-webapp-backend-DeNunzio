package it.unisalento.dashboardiotbackend.dto;

import it.unisalento.dashboardiotbackend.domain.Anomaly;
import it.unisalento.dashboardiotbackend.domain.Route;

import java.time.LocalDate;
import java.util.ArrayList;

public class RouteDTO {
    private String id;
    private String date;
    private String description;
    private ArrayList<Anomaly> anomalies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(ArrayList<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
