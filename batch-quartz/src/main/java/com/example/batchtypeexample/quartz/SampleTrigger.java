package com.example.batchtypeexample.quartz;


import com.example.batchtypeexample.quartz.job.SampleJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SampleTrigger extends AbstractBatchQuartzJobsFactory {

    private final Scheduler scheduler;

    public SampleTrigger(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void batchRun() {
        JobDetail sampleJob = buildJobDetail(SampleJob.class, new HashMap());JobDetail jobDetailB = buildJobDetail(SampleJob.class, new HashMap());
        try{
            scheduler.scheduleJob(sampleJob, buildJobTrigger("*/3 * * * * ?"));
        } catch(SchedulerException e){
            e.printStackTrace();
        }
    }

  /**
   ex) * 1-5,7,8 * * * ?

   *(seconds) *(minutes) *(hours) * (day of month) *(day of week) *(year)
   * : 모두 포함 ? : 해당 필드 고려 X, 일자를 나타내는 필드와 요일을 나타내는 필드는 동시에 설정 할 수 없음으로 둘중 하나는 ? 이어야 함.
   - : 일련의 범위, 2-4는 2, 3, 4를 의미
   , : 일련의 값을 나열 2-4는 2,3,4로 표현 가능
   / : 초기치를 기준으로 일정하게 증가하는 값을 의미, 초를 나타내는 필드에 0/15는 0초를 시작으로 15초씩 증가를 의미 (0, 15, 30, 45)
   매 초마다 실행 : * * * ? * *
   매 분마다 실행 : 0 * * ? * *
   매 시간마다 실행 : 0 0 * ? * *
   매일 0시에 실행 : 0 0 0 * * ?
   매일 1시에 실행 : 0 0 1 * * ?
   매일 1시 15분에 실행 : 0 15 1 * * ?
   */
}
