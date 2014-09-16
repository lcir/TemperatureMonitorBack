package cz.ptw.temperature.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 12:58
 */
@Document(collection = "temperatures")
public class TemperatureInformation extends JsonAbstractObject{

    private String probeId;
    private int temperature = 0;
    private Date date = new Date();

    public TemperatureInformation(final String probeId, final int temperature, final Date date) {
        this(probeId, temperature);
        this.date = date;
    }

    public TemperatureInformation(final String probeId, final int temperature) {
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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
