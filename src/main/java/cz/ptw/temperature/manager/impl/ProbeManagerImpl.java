package cz.ptw.temperature.manager.impl;

import cz.ptw.temperature.dao.ProbesDao;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.manager.ProbeManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:35
 */
public class ProbeManagerImpl implements ProbeManager {

    @Autowired
    private ProbesDao probesDao;

    @Override
    public List<Probe> listAllPossibleProbes() {
        return probesDao.findAllProbes();
    }

    @Override
    public Probe showDetailOfProbe(String probeId) {
        return probesDao.findAProbeIdentifiedByProbeNumber(probeId);
    }

    @Override
    public Probe addNewProbe(Probe newProbe) {
        return probesDao.saveNewProbe(newProbe);
    }

    @Override
    public boolean removeProbeById(String probeId) {
        return probesDao.deleteProbe(probeId);
    }
}
