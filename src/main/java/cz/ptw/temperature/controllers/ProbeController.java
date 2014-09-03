package cz.ptw.temperature.controllers;

import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperaturePeakInterval;
import cz.ptw.temperature.manager.ProbeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 21:21
 */
@Controller
public class ProbeController {

    @Autowired
    private ProbeManager probeManager;

    @RequestMapping(value = "/rest/probe/get", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Probe> listAllPossibleProbes() {
        return probeManager.listAllPossibleProbes();
    }

    @RequestMapping(value = "/rest/probe/get/{probeId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Probe showDetailOfProbe(@PathVariable("probeId") String probeId) {
        return probeManager.showDetailOfProbe(probeId);
    }

    @RequestMapping(value = "/rest/probe/add", method = RequestMethod.GET, params = {"probeId", "locationOfProbe", "lowIntervalPeak", "highIntervalPeak"})
    public void addNewProbe(String probeId, String locationOfProbe, int lowIntervalPeak, int highIntervalPeak) {
        TemperaturePeakInterval temperaturePeakInterval = new TemperaturePeakInterval(lowIntervalPeak, highIntervalPeak);
        probeManager.addNewProbe(new Probe(probeId, locationOfProbe, temperaturePeakInterval));
    }

    @RequestMapping(value = "/rest/probe/remove/{probeId}", method = RequestMethod.GET)
    public void removeProbeById(@PathVariable("probeId") String probeId) {
        probeManager.removeProbeById(probeId);
    }
}
