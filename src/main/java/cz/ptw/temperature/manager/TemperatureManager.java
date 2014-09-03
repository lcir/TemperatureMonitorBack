package cz.ptw.temperature.manager;

import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 12:49
 */
@Service("temperatureManager")
public interface TemperatureManager {
    List<TemperatureInformation> listTemperatureInformationForProbe(String[] probeIds);

    List<TemperatureInformation> listTemperatureInformationForProbe(String[] probeIds, DateInterval dateInterval);

    List<TemperatureInformation> listTemperatureInformationForProbe(DateInterval dateInterval);

    void addNewTemperatureInformationRecord(TemperatureInformation temperatureInformation);
}
