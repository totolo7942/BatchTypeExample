package com.example.batchtypeexample.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class EmailJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Job triggered to send emails");
        JobDataMap map = context.getMergedJobDataMap();
        sendEmail(map);
        log.info("Job completed");
    }

    @SuppressWarnings("unchecked")
    private void sendEmail(JobDataMap map) {
        log.info(">#################### send email");
    }
}
