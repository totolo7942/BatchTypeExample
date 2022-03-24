package com.example.batchtypeexample.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

@Slf4j
@DisallowConcurrentExecution //이중실행 방지
public class SampleExecutorJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap map = context.getMergedJobDataMap();
        executor(map);
    }

    private void executor(JobDataMap map) {
        log.info(">#################### executor Jobs: {} ", map);
    }
}
