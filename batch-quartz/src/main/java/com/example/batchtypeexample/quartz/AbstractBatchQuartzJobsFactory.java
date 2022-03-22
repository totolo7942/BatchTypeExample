package com.example.batchtypeexample.quartz;

import org.quartz.*;

import javax.annotation.PostConstruct;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

public abstract class AbstractBatchQuartzJobsFactory {

  public void batchRun() throws SchedulerException {}

  public Trigger buildJobTrigger(String scheduleExp){
    return TriggerBuilder.newTrigger()
            .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
  }

  public JobDetail buildJobDetail(Class job, Map params){
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.putAll(params);

    return newJob(job).usingJobData(jobDataMap).build();
  }

}