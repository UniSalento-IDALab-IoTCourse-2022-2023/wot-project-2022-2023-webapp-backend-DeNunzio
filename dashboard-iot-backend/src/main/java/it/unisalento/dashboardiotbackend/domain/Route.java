package it.unisalento.dashboardiotbackend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;

@Document("route")
public class Route {
    @Id
    String id;
    private String date;
    private String description;
    private ArrayList<Anomaly> anomalies;

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
