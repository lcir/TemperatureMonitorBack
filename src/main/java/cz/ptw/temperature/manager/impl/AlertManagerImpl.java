package cz.ptw.temperature.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.exceptions.DatabaseFetchingProblem;
import cz.ptw.temperature.manager.AlertManager;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.RegistrantManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:17
 */
public class AlertManagerImpl implements AlertManager {

    @Autowired
    private ProbeManager probeManager;
    @Autowired
    private RegistrantManager registrantManager;
    @Autowired
    private Sender gcmSender;


    @Override
    public void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) throws IOException {

        Message toSendMessage = createAlertMessage(temperatureInformation);
        gcmSender.send(toSendMessage, registrantManager.showAllAvailableRegistrantIds(), 10);

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
