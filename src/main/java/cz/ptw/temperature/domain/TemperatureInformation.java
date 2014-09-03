package cz.ptw.temperature.domain;

import org.joda.time.DateTime;

/**
 * User: t945135
 * DateTime: 3.9.14
 * Time: 12:58
 */
public class TemperatureInformation {

    private String probeId;
    private int temperature = 0;
    private DateTime date;

    public TemperatureInformation(final String probeId, final int temperature, final DateTime date) {
        this.temperature = temperature;
        this.date = date;
        this.probeId = probeId;
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

    public DateTime getDateTime() {
        return date;
    }

    public void setDateTime(DateTime date) {
        this.date = date;
    }
}
