package cz.ptw.temperature.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import cz.ptw.temperature.domain.TemperatureInformation;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 10:51
 */
public interface AlertManager {
    void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) throws JsonProcessingException;

}
