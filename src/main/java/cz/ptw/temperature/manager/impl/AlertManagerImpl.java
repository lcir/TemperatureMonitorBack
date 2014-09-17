package cz.ptw.temperature.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.exceptions.DatabaseFetchingProblem;
import cz.ptw.temperature.manager.AlertManager;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.RegistrantManager;
import cz.ptw.temperature.utils.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:17
 */
@Service("alertManager")
public class AlertManagerImpl implements AlertManager {

    private static final Logger LOG = LoggerFactory.getLogger(AlertManager.class);

    @Autowired
    private ProbeManager probeManager;
    @Autowired
    private RegistrantManager registrantManager;
    @Autowired
    private Sender gcmSender;


    @Override
    public void createMobileTemperaturePeakAlert(TemperatureInformation temperatureInformation) {

        try {
            Message toSendMessage = createAlertMessage(temperatureInformation);
            gcmSender.send(toSendMessage, registrantManager.showAllAvailableRegistrantIds(), 10);
        } catch (Exception ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    private Message createAlertMessage(TemperatureInformation temperatureInformation) throws JsonProcessingException, DatabaseFetchingProblem {

        Probe probeToAlert = probeManager.showDetailOfProbe(temperatureInformation.getProbeId());

        if (probeToAlert == null) {
            throw new DatabaseFetchingProblem();
        }

        return new Message.Builder()
                .addData("probe", JsonWriter.toJson(probeToAlert))
                .addData("temperature", JsonWriter.toJson(temperatureInformation))
                .build();
    }
}
