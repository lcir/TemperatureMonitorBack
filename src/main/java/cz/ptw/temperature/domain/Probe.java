package cz.ptw.temperature.domain;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:23
 */
public class Probe {
    private final String probeId;
    private final String locationOfProbe;
    private final TemperaturePeakInterval temperaturePeakInterval;

    public Probe(final String probeId, final String locationOfProbe, final TemperaturePeakInterval temperaturePeakInterval) {

        this.probeId = probeId;
        this.locationOfProbe = locationOfProbe;
        this.temperaturePeakInterval = temperaturePeakInterval;
    }

    public Probe(final String probeId, final String locationOfProbe) {
        this(probeId, locationOfProbe, new TemperaturePeakInterval(-10, 20));
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


}
