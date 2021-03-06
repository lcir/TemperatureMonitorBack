package cz.ptw.temperature.aspects;

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
        LOG.debug("Running temperature peak check");

        if ((args != null) && (args.length > 0)) {
            if (args[0] instanceof TemperatureInformation) {
                TemperatureInformation temperatureInformation = (TemperatureInformation) args[0];

                if (temperatureManager.checkTemperatureGetPeak(temperatureInformation)) {
                    alertManager.createMobileTemperaturePeakAlert(temperatureInformation);
                    LOG.debug("Temperature with peak");
                    return true;
                }
            }
        }
        LOG.debug("Temperature without peak");
        return false;
    }
}
