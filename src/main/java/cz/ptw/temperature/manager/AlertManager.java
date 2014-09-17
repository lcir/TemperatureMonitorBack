package cz.ptw.temperature.manager;

import cz.ptw.temperature.domain.TemperatureInformation;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 10:51
 */
public interface AlertManager {

    void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) ;

}
