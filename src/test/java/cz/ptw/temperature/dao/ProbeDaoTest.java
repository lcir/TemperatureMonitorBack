package cz.ptw.temperature.dao;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.impl.ProbesDaoImpl;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperaturePeakInterval;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.impl.ProbeManagerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.MyAsserts.assertEquals;
import static com.mongodb.util.MyAsserts.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class ProbeDaoTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private ProbesDao probesDao = new ProbesDaoImpl();
    private Probe toProbeDetail;
    private Query searchProbeQuery;


    @Before
    public void setUp() {
        toProbeDetail = new Probe("1", "At bedroom", new TemperaturePeakInterval(-10, 20));
        searchProbeQuery = new Query(Criteria.where("probeId").is(toProbeDetail.getProbeId()));
    }

    /**
     * Test to find all probes in db
     */
    @Test
    public void findAllProbes() {
        when(mongoOperations.findAll(Probe.class)).thenReturn(MockedData.initializeTestingProbePool(toProbeDetail));
        List<Probe> probeList = probesDao.findAllProbes();
        verify(mongoOperations).findAll(Probe.class);
        assertEquals(probeList.size(), 3);
    }

    /**
     * Test to save a probe to db
     */
    @Test
    public void saveProbeToDb(){

        probesDao.saveNewProbe(toProbeDetail);
        verify(mongoOperations).save(toProbeDetail);
    }

    /**
     * Test to remove probe from db
     */
    @Test
    public void removeProbeFromDb(){

        when(mongoOperations.findOne(searchProbeQuery, Probe.class)).thenReturn(toProbeDetail);

        probesDao.deleteProbe(toProbeDetail.getProbeId());
        verify(mongoOperations).findOne(searchProbeQuery, Probe.class);
        verify(mongoOperations).remove(toProbeDetail);
    }

    /**
     * Test to find detail of probe from db
     */
    @Test
    public void findDetailOProbe(){

        when(mongoOperations.findOne(searchProbeQuery, Probe.class)).thenReturn(toProbeDetail);

        Probe probe = probesDao.findAProbeIdentifiedByProbeNumber(toProbeDetail.getProbeId());
        verify(mongoOperations).findOne(searchProbeQuery, Probe.class);

        assertEquals(probe, toProbeDetail);
    }
}

