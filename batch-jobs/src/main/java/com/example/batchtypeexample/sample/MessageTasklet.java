package com.example.batchtypeexample.sample;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MessageTasklet implements Tasklet {

    private final String message;

    public MessageTasklet(String message) {
        this.message = message;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Message: " + message); // 메세지 출력
        return RepeatStatus.FINISHED;
    }
}
