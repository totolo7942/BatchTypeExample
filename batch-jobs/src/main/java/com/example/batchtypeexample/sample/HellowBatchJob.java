package com.example.batchtypeexample.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HellowBatchJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HellowBatchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job fooJob() {
        return jobBuilderFactory.get("myFooJob") // 일억성이 되는 임의 잡 이름을 지정
                .flow(helloStep()) // 실행하는 Step을 지정
                .end()
                .build();
    }

    @Bean
    public Job barJob() {
        return jobBuilderFactory.get("myBarJob") // 일억성이 되는 임의 잡 이름을 지정
                .flow(helloStep()) // 실행하는 Step을 지정
                .next(worldStep()) // 실행하는 Step을 지정
                .end()
                .build();
    }

    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("myHelloStep") // 임의의 스탭 이름을 지정
                .tasklet(new MessageTasklet("Hello!")) // 실행하는 Tasklet을 지정
                .build();
    }

    @Bean
    public Step worldStep() {
        return stepBuilderFactory.get("myWorldStep") // 임의의 스탭 이름을 지정
                .tasklet(new MessageTasklet("World!")) // 실행하는 Tasklet을 지정
                .build();
    }
}
