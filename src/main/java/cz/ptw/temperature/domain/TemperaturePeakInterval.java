package cz.ptw.temperature.domain;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:47
 */
public class TemperaturePeakInterval {
    private int lowIntervalPeak;
    private int highIntervalPeak;

    public TemperaturePeakInterval(final int lowIntervalPeak, final int highIntervalPeak) {
        this.lowIntervalPeak = lowIntervalPeak;
        this.highIntervalPeak = highIntervalPeak;
    }

    public int getLowIntervalPeak() {
        return lowIntervalPeak;
    }

    public void setLowIntervalPeak(int lowIntervalPeak) {
        this.lowIntervalPeak = lowIntervalPeak;
    }

    public int getHighIntervalPeak() {
        return highIntervalPeak;
    }

    public void setHighIntervalPeak(int highIntervalPeak) {
        this.highIntervalPeak = highIntervalPeak;
    }
}
