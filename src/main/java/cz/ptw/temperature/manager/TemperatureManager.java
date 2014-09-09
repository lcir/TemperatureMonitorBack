package cz.ptw.temperature.manager;

import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 12:49
 */
public interface TemperatureManager {
    List<TemperatureInformation> listTemperatureInformationForProbe(String[] probeIds);

    List<TemperatureInformation> listTemperatureInformationForProbe(String[] probeIds, DateInterval dateInterval);

    List<TemperatureInformation> listTemperatureInformationForProbe(DateInterval dateInterval);

    List<TemperatureInformation> listTemperatureInformationForProbe(List<Probe> probes);

    void addNewTemperatureInformationRecord(TemperatureInformation temperatureInformation);

    List<TemperatureInformation> listTemperatureInformationForProbe();

    boolean checkTemperatureGetPeak(TemperatureInformation temperatureInformation);
}
