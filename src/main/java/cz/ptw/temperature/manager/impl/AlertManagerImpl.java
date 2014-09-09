package cz.ptw.temperature.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.exceptions.DatabaseFetchingProblem;
import cz.ptw.temperature.manager.AlertManager;
import cz.ptw.temperature.manager.ProbeManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:17
 */
public class AlertManagerImpl implements AlertManager {

    @Autowired
    private ProbeManager probeManager;

    @Override
    public void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) throws JsonProcessingException {

        Message toSendMessage = createAlertMessage(temperatureInformation);

    }

    private Message createAlertMessage(TemperatureInformation temperatureInformation) throws JsonProcessingException, DatabaseFetchingProblem {

        Probe probeToAlert = probeManager.showDetailOfProbe(temperatureInformation.getProbeId());

        if (probeToAlert == null) {
            throw new DatabaseFetchingProblem();
        }

        return new Message.Builder()
                .addData("probe", probeToAlert.toJson())
                .addData("temperature", temperatureInformation.toJson())
                .build();
    }
}
