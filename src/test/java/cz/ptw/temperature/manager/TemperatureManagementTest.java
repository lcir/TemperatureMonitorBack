package cz.ptw.temperature.manager;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.TemperatureDao;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.TemperatureManager;
import cz.ptw.temperature.manager.impl.TemperatureManagerImpl;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing class for temperature management
 * Test some different variation of listing some temperature data for graph plot.
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperatureManagementTest {

    @InjectMocks
    private TemperatureManager temperatureManager = new TemperatureManagerImpl();
    @Mock
    private TemperatureDao temperatureDao;

    private List<TemperatureInformation> listOfTemperatures;
    private DateInterval dateInterval;

    @Before
    public void setUp() {
        dateInterval = new DateInterval(new DateTime(), new DateTime().plusDays(5));
    }

    /**
     * Test for listing all temperatures for one probe
     */
    @Test
    public void listAllTemperatureInformationForOneProbe() {
        String[] probeNumbers = {"1"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeNumbers);

        when(temperatureDao.findTemperatureInformationForProbe(probeNumbers)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureManager.listTemperatureInformationForProbe(probeNumbers);
        verify(temperatureDao).findTemperatureInformationForProbe(probeNumbers);

        Assert.assertEquals(temperatureInformations.size(), 4);
    }

    /**
     * Test for listing all temperatures for all probes
     */
    @Test
    public void listAllTemperatureInformationForAllProbes() {
        String[] probeNumbers = {"1","2","3"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeNumbers);

        when(temperatureDao.findTemperatureInformationForProbe(dateInterval)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureManager.listTemperatureInformationForProbe(dateInterval);
        verify(temperatureDao).findTemperatureInformationForProbe(dateInterval);

        Assert.assertEquals(temperatureInformations.size(), 12);
    }



    /**
     * Test for listing temperatures for one probe in date interval
     */
    @Test
    public void listTemperatureInformationFromDateIntervalForOneProbe() {
        String[] probeNumbers = {"1"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeNumbers);

        when(temperatureDao.findTemperatureInformationForProbe(probeNumbers, dateInterval)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureManager.listTemperatureInformationForProbe(probeNumbers, dateInterval);
        verify(temperatureDao).findTemperatureInformationForProbe(probeNumbers, dateInterval);

        Assert.assertEquals(temperatureInformations.size(), 4);
    }


    /**
     * Test for listing all temperatures for multiple probes
     */
    @Test
    public void listAllTemperatureInformationForMultipleProbes() {
        String[] probeNumbers = {"1", "4"};
        listOfTemperatures = MockedData.fillTemperatureListByValues(probeNumbers);

        when(temperatureDao.findTemperatureInformationForProbe(probeNumbers)).thenReturn(listOfTemperatures);
        List<TemperatureInformation> temperatureInformations = temperatureManager.listTemperatureInformationForProbe(probeNumbers);
        verify(temperatureDao).findTemperatureInformationForProbe(probeNumbers);

        Assert.assertEquals(temperatureInformations.size(), 8);
    }

    /**
     * Test to add new temperature information
     */
    @Test
    public void addNewTemperatureInformationByOneProbe(){

        TemperatureInformation temperatureInformation = new TemperatureInformation("1", -30, new DateTime());
        temperatureManager.addNewTemperatureInformationRecord(temperatureInformation);
        verify(temperatureDao).saveTemperatureInformationToDb(temperatureInformation);
    }
}
