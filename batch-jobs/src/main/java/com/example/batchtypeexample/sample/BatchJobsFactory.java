package com.example.batchtypeexample.sample;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class BatchJobsFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Job getBatchJob(final String beanName, JobLauncher launcher){
        JobParametersBuilder jobParametersbuilder = new JobParametersBuilder();
        JobParameters jobParameters = jobParametersbuilder.toJobParameters();

        Job job = (Job) applicationContext.getBean("fooJob");
        JobExecution jobExecution = null;
        try {
            jobExecution = launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

        return job;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
