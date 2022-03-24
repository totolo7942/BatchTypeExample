package com.example.batchtypeexample.sample.scheduler;

import com.example.batchtypeexample.sample.BatchJobsFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
@Slf4j
public class HelloScheduler {

    private final JobLauncher launcher;
    private final BatchJobsFactory batchJobsFactory;

    public HelloScheduler(JobLauncher launcher, BatchJobsFactory batchJobsFactory) {
        this.launcher = launcher;
        this.batchJobsFactory = batchJobsFactory;
    }

//    @Scheduled(cron="*/3 * * * * ?")
    public void executor() {

        Job job = batchJobsFactory.getBatchJob("fooJob", launcher);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("key", String.valueOf(randomUUID()));
        JobParameters parameters = jobParametersBuilder.toJobParameters();
        JobExecution jobExecution = null;
        try {
            jobExecution = launcher.run(job, parameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
        log.info("## status {}" , jobExecution.getStatus());
    }
}
