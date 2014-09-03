package cz.ptw.temperature.manager;

import cz.ptw.temperature.domain.Probe;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:27
 */
public interface ProbeManager {
    List<Probe> listAllPossibleProbes();

    Probe showDetailOfProbe(String probeId);

    Probe addNewProbe(Probe newProbe);

    boolean removeProbeById(String probeId);
}
