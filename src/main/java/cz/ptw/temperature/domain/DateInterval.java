package cz.ptw.temperature.domain;

import org.joda.time.DateTime;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 15:02
 */
public class DateInterval {
    private DateTime startDateInterval;
    private DateTime finishDateInterval;

    public DateInterval(final DateTime startDateInterval, final DateTime finishDateInterval) {
        this.startDateInterval = startDateInterval;
        this.finishDateInterval = finishDateInterval;
    }

    public DateTime getStartDateInterval() {
        return startDateInterval;
    }

    public void setStartDateInterval(DateTime startDateInterval) {
        this.startDateInterval = startDateInterval;
    }

    public DateTime getFinishDateInterval() {
        return finishDateInterval;
    }

    public void setFinishDateInterval(DateTime finishDateInterval) {
        this.finishDateInterval = finishDateInterval;
    }
}
