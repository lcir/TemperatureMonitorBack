package cz.ptw.temperature.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.TemperatureDao;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.AlertManager;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.TemperatureManager;
import cz.ptw.temperature.manager.impl.TemperatureManagerImpl;
import junit.framework.TestCase;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static com.mongodb.util.MyAsserts.assertFalse;
import static com.mongodb.util.MyAsserts.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing class for temperature management
 * Test some different variation of listing some temperature data for graph plot.
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperaturePeakAspectTest {

    @InjectMocks
    private TemperaturePeakCheckAspect temperaturePeakCheckAspect = new TemperaturePeakCheckAspect();
    @Mock
    private TemperatureManager temperatureManager;
    @Mock
    private AlertManager alertManager;
    private TemperatureInformation temperatureInformation;
    private JoinPoint joinPoint;

    @Before
    public void setUp() {
        temperatureInformation = new TemperatureInformation("1", -30, new DateTime().toDate());
        joinPoint = this.testingJoinPointFactory();
    }

    /**
     * Test invoking of aspect. This test will be positive
     */
    @Test
    public void invokingAspectWithPositiveValue() throws IOException {
        when(temperatureManager.checkTemperatureGetPeak(temperatureInformation)).thenReturn(true);

        boolean resultOfAspect = temperaturePeakCheckAspect.checkTemperaturePeak(joinPoint);

        verify(alertManager).createMobileTemperaturePeakAlert(temperatureInformation);
        verify(temperatureManager).checkTemperatureGetPeak(temperatureInformation);
        assertTrue(resultOfAspect);
    }

    /**
     * Test invoking of aspect. This test will be negative
     */
    @Test
    public void invokingAspectWithNegativeValue() {
        when(temperatureManager.checkTemperatureGetPeak(temperatureInformation)).thenReturn(false);

        boolean resultOfAspect = temperaturePeakCheckAspect.checkTemperaturePeak(joinPoint);

        verify(temperatureManager).checkTemperatureGetPeak(temperatureInformation);
        assertFalse(resultOfAspect);
    }

    private JoinPoint testingJoinPointFactory() {
        return new JoinPoint() {
            @Override
            public String toShortString() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String toLongString() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getThis() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getTarget() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object[] getArgs() {
                return new Object[]{temperatureInformation};  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Signature getSignature() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public SourceLocation getSourceLocation() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getKind() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public StaticPart getStaticPart() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

}
