package cz.ptw.temperature.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.RegistrantDao;
import cz.ptw.temperature.domain.Phone;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.impl.AlertManagerImpl;
import cz.ptw.temperature.manager.impl.RegistrantManagerImpl;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Testing class for temperature management
 * Test some different variation of listing some temperature data for graph plot.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrantManagerTest {

    @InjectMocks
    private RegistrantManager registrantManager = new RegistrantManagerImpl();
    @Mock
    private RegistrantDao registrantDao;

    /**
     * Test to get all registrant ids
     */
    @Test
    public void showAllRegistrantIds(){

        when(registrantDao.showAllMobilePhones()).thenReturn(MockedData.initializeSomeMobilePhones());
        registrantManager.showAllAvailableRegistrantIds();
        verify(registrantDao).showAllMobilePhones();
    }

    /**
     * Test to register new mobile phone
     */
    @Test
    public void addNewMobilePhone(){

        Phone mockedMobilePhone = MockedData.initializeNewMobilePhone();;
        registrantManager.addNewMobilePhone(mockedMobilePhone);

        verify(registrantDao).addNewMobilePhone(mockedMobilePhone);
    }
}
