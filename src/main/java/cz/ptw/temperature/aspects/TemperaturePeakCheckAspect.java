package cz.ptw.temperature.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.AlertManager;
import cz.ptw.temperature.manager.TemperatureManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 10:36
 */
@Aspect
public class TemperaturePeakCheckAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TemperaturePeakCheckAspect.class);

    @Autowired
    private TemperatureManager temperatureManager;
    @Autowired
    private AlertManager alertManager;

    @After("execution(* cz.ptw.temperature.manager.TemperatureManager.addNewTemperatureInformationRecord(..))")
    public boolean checkTemperaturePeak(final JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        if ((args != null) && (args.length > 0)) {
            if (args[0] instanceof TemperatureInformation) {
                TemperatureInformation temperatureInformation = (TemperatureInformation) args[0];

                if (temperatureManager.checkTemperatureGetPeak(temperatureInformation)) {
                    try {
                        alertManager.createMobileTemperaturePeakAlert(temperatureInformation);
                        return true;
                    } catch (IOException e) {
                        LOG.error("Sending of message fails");
                    }
                }
            }
        }
        return false;
    }
}