package cz.ptw.temperature.dao;

import cz.ptw.temperature.domain.Probe;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:18
 */
public interface ProbesDao {
    Probe findAProbeIdentifiedByProbeNumber(String probeId);

    List<Probe> findAllProbes();

    Probe saveNewProbe(Probe toSaveProbe);

    boolean deleteProbe(String probeId);
}
