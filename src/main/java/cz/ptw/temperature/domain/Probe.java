package cz.ptw.temperature.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:23
 */
@Document(collection = "probes")
public class Probe extends JsonAbstractObject {

    @Id
    private String probeId;
    private String locationOfProbe;
    private TemperaturePeakInterval temperaturePeakInterval;

    public Probe(final String probeId, final String locationOfProbe, final TemperaturePeakInterval temperaturePeakInterval) {

        this.probeId = probeId;
        this.locationOfProbe = locationOfProbe;
        this.temperaturePeakInterval = temperaturePeakInterval;
    }

    public Probe(final String probeId, final String locationOfProbe) {
        this(probeId, locationOfProbe, new TemperaturePeakInterval(-10, 20));
    }

    public Probe() {
    }

    public String getProbeId() {
        return probeId;
    }

    public String getLocationOfProbe() {
        return locationOfProbe;
    }

    public TemperaturePeakInterval getTemperaturePeakInterval() {
        return temperaturePeakInterval;
    }


    public boolean checkTemperaturePeakInterval(TemperatureInformation temperatureInformation) {
        if ((temperaturePeakInterval.getLowIntervalPeak() >= temperatureInformation.getTemperature()) ||
                (temperaturePeakInterval.getHighIntervalPeak() <= temperatureInformation.getTemperature())) {
            return true;
        }
        return false;
    }
}
