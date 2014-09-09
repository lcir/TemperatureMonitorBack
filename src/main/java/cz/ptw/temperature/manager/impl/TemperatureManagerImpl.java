package cz.ptw.temperature.manager.impl;

import cz.ptw.temperature.dao.TemperatureDao;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.TemperatureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:17
 */
@Service("temperatureManager")
public class TemperatureManagerImpl implements TemperatureManager {

    @Autowired
    private TemperatureDao temperatureDao;
    @Autowired
    private ProbeManager probeManager;

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

    @Override
    public List<TemperatureInformation> listTemperatureInformationForProbe(List<Probe> probes) {
        List<String> probesIdList = new ArrayList<String>();
        probes.forEach((c) -> probesIdList.add(c.getProbeId()));
        return this.listTemperatureInformationForProbe(probesIdList.toArray(new String[probesIdList.size()]));
    }
    @Override
    public List<TemperatureInformation> listTemperatureInformationForProbe() {
        List<String> probesIdList = new ArrayList<String>();
        probeManager.listAllPossibleProbes().forEach((c) -> probesIdList.add(c.getProbeId()));
        return this.listTemperatureInformationForProbe(probesIdList.toArray(new String[probesIdList.size()]));
    }

    @Override
    public boolean checkTemperatureGetPeak(TemperatureInformation temperatureInformation) {
        Probe temperatureProbe = probeManager.showDetailOfProbe(temperatureInformation.getProbeId());
        return temperatureProbe.checkTemperaturePeakInterval(temperatureInformation);
    }

    @Override
    public void addNewTemperatureInformationRecord(TemperatureInformation temperatureInformation) {
        temperatureDao.saveTemperatureInformationToDb(temperatureInformation);
    }
}
