package cz.ptw.temperature.manager;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.ProbesDao;
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

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.MyAsserts.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class ProbeManagementTest {

    @Mock
    private ProbesDao probesDao;

    @InjectMocks
    private ProbeManager probeManager = new ProbeManagerImpl();
    private List<Probe> allPossibleProbes;

    private Probe toProbeDetail;
    private String probeId = "1";

    @Before
    public void setUp() {
        toProbeDetail = MockedData.initializeFirstProbe();

    }

    /**
     * Test to listing all possible probes
     */
    @Test
    public void listingAllPossibleProbes() {

        allPossibleProbes = MockedData.initializeTestingProbePool(toProbeDetail);
        when(probesDao.findAllProbes()).thenReturn(allPossibleProbes);

        List<Probe> probeList = probeManager.listAllPossibleProbes();

        verify(probesDao).findAllProbes();
        Assert.assertEquals(probeList.size(), allPossibleProbes.size());
    }

    /**
     * Test to printing detail about selected probe
     */
    @Test
    public void showDetailOfSelectedProbe() {

        when(probesDao.findAProbeIdentifiedByProbeNumber(probeId)).thenReturn(toProbeDetail);

        Probe detailProbe = probeManager.showDetailOfProbe(probeId);

        verify(probesDao).findAProbeIdentifiedByProbeNumber(probeId);
        Assert.assertEquals(detailProbe.getProbeId(), probeId);
    }

    /**
     * Test to add new probe
     */
    @Test
    public void addNewProbe() {

        when(probesDao.saveNewProbe(toProbeDetail)).thenReturn(toProbeDetail);
        Probe newProbe = probeManager.addNewProbe(toProbeDetail);

        verify(probesDao).saveNewProbe(toProbeDetail);

        Assert.assertEquals(newProbe, toProbeDetail);
    }

    /**
     * Test to remove probe
     */
    @Test
    public void removeProbeById() {
        when(probesDao.deleteProbe(probeId)).thenReturn(true);

        boolean isDeleted = probeManager.removeProbeById(probeId);

        verify(probesDao).deleteProbe(probeId);

        assertTrue(isDeleted);
    }
}

