package cz.ptw.temperature.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.impl.AlertManagerImpl;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing class for temperature management
 * Test some different variation of listing some temperature data for graph plot.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlertManagerTest {

    @InjectMocks
    private AlertManager alertManager = new AlertManagerImpl();
    @Mock
    private ProbeManager probeManager;
    private TemperatureInformation temperatureInformation;
    private Message message;
    private Method createAlertMessage;

    @Before
    public void setUp() throws NoSuchMethodException {
        temperatureInformation = new TemperatureInformation("1", -30, new DateTime().toDate());
        createAlertMessage = AlertManagerImpl.class.getDeclaredMethod("createAlertMessage", new Class[]{TemperatureInformation.class});
        createAlertMessage.setAccessible(true);

        try {
            message = this.messageBuilder();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    /**
     * Test if the message will be well created
     */
    @Test
    public void validationOfCreatedMessage() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        when(probeManager.showDetailOfProbe(temperatureInformation.getProbeId())).thenReturn(MockedData.initializeFirstProbe());

        Message messageToValidation = (Message) createAlertMessage.invoke(alertManager, new Object[]{temperatureInformation});

        verify(probeManager).showDetailOfProbe(temperatureInformation.getProbeId());
        assertEquals(messageToValidation.getData().get("probe"), message.getData().get("probe"));
    }

    /**
     * Test if the message will be well created - probe will not be found
     */
    @Test(expected = InvocationTargetException.class)
    public void validationOfCreatedMessageProbeWillNotBeFound() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        when(probeManager.showDetailOfProbe(temperatureInformation.getProbeId())).thenReturn(null);
        createAlertMessage.invoke(alertManager, new Object[]{temperatureInformation});
    }

    /**
     * Test sending message to mobile phones
     */
    @Test
    public void checkIfMessageWillBeSend() throws Exception {
        when(probeManager.showDetailOfProbe(temperatureInformation.getProbeId())).thenReturn(MockedData.initializeFirstProbe());
        alertManager.createMobileTemperaturePeakAlert(temperatureInformation);
        verify(probeManager).showDetailOfProbe(temperatureInformation.getProbeId());
    }

    private Message messageBuilder() throws JsonProcessingException {

        return new Message.Builder()
                .addData("probe", MockedData.initializeFirstProbe().toJson())
                .addData("temperature", temperatureInformation.toJson())
                .build();

    }
}
