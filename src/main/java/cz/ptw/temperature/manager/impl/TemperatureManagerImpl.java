package cz.ptw.temperature.manager.impl;

import cz.ptw.temperature.dao.TemperatureDao;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.TemperatureManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:17
 */
public class TemperatureManagerImpl implements TemperatureManager {

    @Autowired
    private TemperatureDao temperatureDao;

    @Override
    public List<TemperatureInformation> listTemperatureInformationForProbe(final String[] probeIds) {
        return temperatureDao.findTemperatureInformationForProbe(probeIds);
    }

    @Override
    public List<TemperatureInformation> listTemperatureInformationForProbe(final String[] probeIds, DateInterval dateInterval) {
        return temperatureDao.findTemperatureInformationForProbe(probeIds, dateInterval);
    }

    @Override
    public List<TemperatureInformation> listTemperatureInformationForProbe(DateInterval dateInterval) {
        return temperatureDao.findTemperatureInformationForProbe(dateInterval);
    }
}
