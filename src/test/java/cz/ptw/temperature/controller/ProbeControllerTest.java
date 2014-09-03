package cz.ptw.temperature.controller;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.controllers.ProbeController;
import cz.ptw.temperature.dao.ProbesDao;
import cz.ptw.temperature.dao.impl.ProbesDaoImpl;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperaturePeakInterval;
import cz.ptw.temperature.manager.ProbeManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.mongodb.util.MyAsserts.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class ProbeControllerTest {

    @Mock
    private ProbeManager probeManager;

    @InjectMocks
    private ProbeController probeController = new ProbeController();
    private Probe firstProbe;

    @Before
    public void setUp() {
        firstProbe = MockedData.initializeFirstProbe();

    }

    /**
     * Test to list all probes in json
     */
    @Test
    public void listAllPossibleProbes() {

        List<Probe> listOfAllProbes = MockedData.initializeTestingProbePool(firstProbe);

        when(probeManager.listAllPossibleProbes()).thenReturn(listOfAllProbes);

        List<Probe> probes = probeController.listAllPossibleProbes();

        verify(probeManager).listAllPossibleProbes();
        assertEquals(probes.size(), listOfAllProbes.size());
    }

    /**
     * Test to get detail of single probe by ID
     */
    @Test
    public void getDetailOfSingleProbeById() {

        when(probeManager.showDetailOfProbe(firstProbe.getProbeId())).thenReturn(firstProbe);

        Probe probe = probeController.showDetailOfProbe(firstProbe.getProbeId());

        verify(probeManager).showDetailOfProbe(firstProbe.getProbeId());
        assertEquals(probe, firstProbe);
    }

    /**
     * Test to add new probe
     */
    @Test
    public void addNewProbeFromRequest() {

        probeController.addNewProbe(firstProbe.getProbeId(), firstProbe.getLocationOfProbe(),
                firstProbe.getTemperaturePeakInterval().getLowIntervalPeak(),
                firstProbe.getTemperaturePeakInterval().getHighIntervalPeak());

        verify(probeManager).addNewProbe((Probe) anyObject());
    }

    /**
     * Test to remove probe
     */
    @Test
    public void removeProbeFromRequest() {

        probeController.removeProbeById(firstProbe.getProbeId());

        verify(probeManager).removeProbeById(firstProbe.getProbeId());
    }

}

