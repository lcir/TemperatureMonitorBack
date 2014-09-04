package cz.ptw.temperature.dao;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.impl.ProbesDaoImpl;
import cz.ptw.temperature.dao.impl.TemperatureDaoImpl;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;
import org.joda.time.DateTime;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperatureDaoTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private TemperatureDao temperatureDao = new TemperatureDaoImpl();
    private Query searchTemperatureQuery;
    private String[] probeIds = {"1", "2", "3"};
    private List<TemperatureInformation> listOfMockedTemperatureInformatins;

    @Before
    public void setUp() {
        listOfMockedTemperatureInformatins = MockedData.fillTemperatureListByValues(probeIds);
    }

    /**
     * Test to save temperature information to db
     */
    @Test
    public void saveNewTemperatureInformationIntoDb() {

        TemperatureInformation temperatureInformation = new TemperatureInformation("1", 20);

        temperatureDao.saveTemperatureInformationToDb(temperatureInformation);
        verify(mongoOperations).save(temperatureInformation);
    }

    /**
     * Test to get temperature information for some probes
     */
    @Test
    public void listOfPossibleTemperatureInformation() {

        searchTemperatureQuery = new Query(Criteria.where("probeId").in(probeIds));
        when(mongoOperations.find(searchTemperatureQuery, TemperatureInformation.class)).thenReturn(listOfMockedTemperatureInformatins);

        List<TemperatureInformation> listOfTemperatures = temperatureDao.findTemperatureInformationForProbe(probeIds);

        verify(mongoOperations).find(searchTemperatureQuery, TemperatureInformation.class);
        assertEquals(listOfTemperatures.size(), listOfMockedTemperatureInformatins.size());
    }

    /**
     * Test to get temperature information during datetime interval
     */
    @Test
    public void listOfPossibleTemperatureInformationForProbesDuringDateTimeInterval() {

        DateInterval dateInterval = new DateInterval(new DateTime().plusDays(0), new DateTime().plusDays(6));
        searchTemperatureQuery = new Query(Criteria.where("probeId").in(probeIds).and("date").gt(dateInterval.getStartDateInterval().toDate()).lte(dateInterval.getFinishDateInterval().toDate()));

        when(mongoOperations.find(searchTemperatureQuery, TemperatureInformation.class)).thenReturn(listOfMockedTemperatureInformatins);

        List<TemperatureInformation> listOfTemperatures = temperatureDao.findTemperatureInformationForProbe(probeIds, dateInterval);

        verify(mongoOperations).find(searchTemperatureQuery, TemperatureInformation.class);
        assertEquals(listOfTemperatures.size(), listOfMockedTemperatureInformatins.size());
    }
}

