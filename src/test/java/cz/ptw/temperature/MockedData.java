package cz.ptw.temperature;

import cz.ptw.temperature.domain.Probe;
import cz.ptw.temperature.domain.TemperaturePeakInterval;

import java.util.ArrayList;
import java.util.List;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 20:51
 */
public class MockedData {

    public static List<Probe> initializeTestingProbePool(Probe firstProbe) {

        List<Probe> testedProbesPool = new ArrayList<Probe>();

        testedProbesPool.add(firstProbe);
        testedProbesPool.add(new Probe("2", "At Closet", new TemperaturePeakInterval(-10, 20)));
        testedProbesPool.add(new Probe("3", "At bar"));

        return testedProbesPool;
    }

    public static Probe initializeFirstProbe() {
        return new Probe("1", "At bedroom", new TemperaturePeakInterval(-10, 20));
    }
}
