package cz.ptw.temperature.dao;

import cz.ptw.temperature.domain.Probe;
import org.springframework.stereotype.Repository;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 13:18
 */
@Repository("probesDao")
public interface ProbesDao {
    Probe findAProbeIdentifiedByProbeNumber(int probeNumber);
}
