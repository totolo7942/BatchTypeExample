package com.example.batchtypeexample.dto;

import com.example.batchtypeexample.job.SampleExecutorJob;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;

@Data
public class JobDescriptor {
    @NotBlank
    private String name;
    private String group;
    @JsonProperty("triggers")
    private List<TriggerDescriptor> triggerDescriptors = new ArrayList<>();

    public JobDescriptor setName(final String name) {
        this.name = name;
        return this;
    }

    public JobDescriptor setGroup(final String group) {
        this.group = group;
        return this;
    }


    public JobDescriptor setTriggerDescriptors(final List<TriggerDescriptor> triggerDescriptors) {
        this.triggerDescriptors = triggerDescriptors;
        return this;
    }

    /**
     * Convenience method for building Triggers of Job
     *
     * @return Triggers for this JobDetail
     */
    @JsonIgnore
    public Set<Trigger> buildTriggers() {
        Set<Trigger> triggers = new LinkedHashSet<>();
        for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
            triggers.add(triggerDescriptor.buildTrigger());
        }

        return triggers;
    }

    /**
     * Convenience method that builds a JobDetail
     *
     * @return the JobDetail built from this descriptor
     */
    public JobDetail buildJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        return newJob(SampleExecutorJob.class)
                .withIdentity(getName(), getGroup())
                .usingJobData(jobDataMap)
                .build();
    }

    /**
     * Convenience method that builds a descriptor from JobDetail and Trigger(s)
     *
     * @param jobDetail
     *            the JobDetail instance
     * @param triggersOfJob
     *            the Trigger(s) to associate with the Job
     * @return the JobDescriptor
     */
    @SuppressWarnings("unchecked")
    public static JobDescriptor buildDescriptor(JobDetail jobDetail, List<? extends Trigger> triggersOfJob) {
        // @formatter:off
        List<TriggerDescriptor> triggerDescriptors = new ArrayList<>();

        for (Trigger trigger : triggersOfJob) {
            triggerDescriptors.add(TriggerDescriptor.buildDescriptor(trigger));
        }

        return new JobDescriptor()
                .setName(jobDetail.getKey().getName())
                .setGroup(jobDetail.getKey().getGroup())
                .setTriggerDescriptors(triggerDescriptors);
        // @formatter:on
    }
}
