package cz.ptw.temperature.dao;

import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:10
 */
public interface TemperatureDao {

    List<TemperatureInformation> findTemperatureInformationForProbe(String[] probeIds);

    List<TemperatureInformation> findTemperatureInformationForProbe(DateInterval dateInterval);

    List<TemperatureInformation> findTemperatureInformationForProbe(String[] probeIds, DateInterval dateInterval);

    void saveTemperatureInformationToDb(TemperatureInformation temperatureInformation);
}
