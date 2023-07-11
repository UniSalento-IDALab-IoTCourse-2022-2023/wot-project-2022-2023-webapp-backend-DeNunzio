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
    private String startClock;
    private String endClock;
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

    public String getStartClock() {
        return startClock;
    }

    public void setStartClock(String startClock) {
        this.startClock = startClock;
    }

    public String getEndClock() {
        return endClock;
    }

    public void setEndClock(String endClock) {
        this.endClock = endClock;
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
    public void addAnomaly(Anomaly newAnomaly){
        this.anomalies.add(newAnomaly);
    }
}
