package cz.ptw.temperature.manager.impl;

import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.AlertManager;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:17
 */
public class AlertManagerImpl implements AlertManager {

    @Override
    public void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) {
        System.out.println("Alert");
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
