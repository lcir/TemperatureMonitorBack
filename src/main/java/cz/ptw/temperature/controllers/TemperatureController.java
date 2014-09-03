package cz.ptw.temperature.controllers;

import cz.ptw.temperature.domain.TemperatureInformation;
import cz.ptw.temperature.manager.ProbeManager;
import cz.ptw.temperature.manager.TemperatureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 22:00
 */
@Controller
public class TemperatureController {

    @Autowired
    private TemperatureManager temperatureManager;

    @RequestMapping(value = "/rest/temp/get", method = RequestMethod.GET)
    public
    @ResponseBody
    List<TemperatureInformation> listAllTemperatures() {
        return temperatureManager.listTemperatureInformationForProbe();
    }

    @RequestMapping(value = "/rest/temp/get", method = RequestMethod.GET, params = {"probeIds"})
    public
    @ResponseBody
    List<TemperatureInformation> listOfTemperaturesFromProbes(@RequestParam("probeIds") String[] probeIds) {
        return temperatureManager.listTemperatureInformationForProbe(probeIds);
    }

    @RequestMapping(value = "/rest/temp/get", method = RequestMethod.GET, params = {"probeId", "temperature"})
    public void addTemperatureInformation(@RequestParam("probeId") String probeId, @RequestParam("temperature") int temperature) {
        temperatureManager.addNewTemperatureInformationRecord(new TemperatureInformation(probeId, temperature));
    }
}
