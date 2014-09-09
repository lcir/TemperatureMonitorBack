package cz.ptw.temperature.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * User: T945135
 * Date: 8.9.14
 * Time: 10:22
 */
@Component
public class PeakDetectJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {



    }
}
