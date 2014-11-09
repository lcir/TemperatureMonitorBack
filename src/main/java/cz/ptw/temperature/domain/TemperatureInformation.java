package cz.ptw.temperature.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 12:58
 */
@Document(collection = "temperatures")
public class TemperatureInformation {

    private String probeId;
    private double temperature = 0d;
    private Date date = new Date();

    public TemperatureInformation(final String probeId, final double temperature, final Date date) {
        this(probeId, temperature);
        this.date = date;
    }

    public TemperatureInformation(final String probeId, final double temperature) {
        this.temperature = temperature;
        this.probeId = probeId;
    }

    public TemperatureInformation() {
    }

    public String getProbeId() {
        return probeId;
    }

    public void setProbeId(String probeId) {
        this.probeId = probeId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
