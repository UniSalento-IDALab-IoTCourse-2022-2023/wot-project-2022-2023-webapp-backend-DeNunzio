package it.unisalento.dashboardiotbackend.dto;

import it.unisalento.dashboardiotbackend.domain.Anomaly;
import it.unisalento.dashboardiotbackend.domain.Route;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;

public class RouteDTO {
    String id;
    private String dateStart;
    private String dateEnd;
    private String description;
    private ArrayList<Anomaly> anomalies;

    public String getDateStart() {
        return dateStart;
    }
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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
