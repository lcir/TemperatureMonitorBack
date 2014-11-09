package cz.ptw.temperature.controller;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.controllers.TemperatureController;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.TemperatureManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTest {

    @Mock
    private TemperatureManager temperatureManager;
    @Mock
    private ProbeManager probeManager;

    @InjectMocks
    private TemperatureController temperatureController;
    private List<TemperatureInformation> listOfTemperatures;

    @Before
    public void setUp() {
    }

    /**
     * Test for all temperatures
     */
    @Test
    public void findAllTemperatures() {
        String[] probeIds = {"1", "2", "3"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeIds);

        when(temperatureManager.listTemperatureInformationForProbe()).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureController.listAllTemperatures();

        verify(temperatureManager).listTemperatureInformationForProbe();
        assertEquals(temperatureInformations.size(), listOfTemperatures.size());
    }

    /**
     * Test for one probe
     */
    @Test
    public void findTemperaturesForOneProbe() {
        String[] probeIds = {"1"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeIds);

        when(temperatureManager.listTemperatureInformationForProbe(probeIds)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureController.listOfTemperaturesFromProbes(probeIds);

        verify(temperatureManager).listTemperatureInformationForProbe(probeIds);
        assertEquals(temperatureInformations.size(), listOfTemperatures.size());
    }

    /**
     * Test for multiple probes
     */
    @Test
    public void findTemperaturesForMultipleProbe() {
        String[] probeIds = {"1", "2", "3"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeIds);

        when(temperatureManager.listTemperatureInformationForProbe(probeIds)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureController.listOfTemperaturesFromProbes(probeIds);

        verify(temperatureManager).listTemperatureInformationForProbe(probeIds);
        assertEquals(temperatureInformations.size(), listOfTemperatures.size());
    }

    /**
     * Test for one probe in date interval
     */
    @Test
    public void findAllTemperaturesForOneProbeAndAroundDateInterval() {
        String[] probeIds = {"1"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeIds);

        when(temperatureManager.listTemperatureInformationForProbe(probeIds)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureController.listOfTemperaturesFromProbes(probeIds);

        verify(temperatureManager).listTemperatureInformationForProbe(probeIds);
        assertEquals(temperatureInformations.size(), listOfTemperatures.size());
    }

    /**
     * Test for adding new temperature information
     */
    @Test
    public void addingNewTemperatureInformation() {
        String probeId = "1";
        double temperature = 20d;
        temperatureController.addTemperatureInformation(probeId, temperature);
        verify(temperatureManager).addNewTemperatureInformationRecord(anyObject());
    }
}

