package cz.ptw.temperature.dao.impl;

import cz.ptw.temperature.dao.ProbesDao;
import cz.ptw.temperature.domain.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 20:45
 */
@Repository("probesDao")
public class ProbesDaoImpl implements ProbesDao {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public Probe findAProbeIdentifiedByProbeNumber(String probeId) {
        Query findOneByProbeIdQuery = new Query(Criteria.where("probeId").is(probeId));
        return mongoOperation.findOne(findOneByProbeIdQuery, Probe.class);
    }

    @Override
    public List<Probe> findAllProbes() {
        return mongoOperation.findAll(Probe.class);
    }

    @Override
    public Probe saveNewProbe(Probe toSaveProbe) {
        mongoOperation.save(toSaveProbe);

        return toSaveProbe;
    }

    @Override
    public boolean deleteProbe(String probeId) {

        Probe toDeleteProbe = this.findAProbeIdentifiedByProbeNumber(probeId);
        mongoOperation.remove(toDeleteProbe);

        return true;
    }
}
