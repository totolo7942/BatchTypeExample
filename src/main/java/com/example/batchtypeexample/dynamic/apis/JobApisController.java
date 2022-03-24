package com.example.batchtypeexample.dynamic.apis;

import com.example.batchtypeexample.dto.JobDescriptor;
import com.example.batchtypeexample.services.SampleJobsService;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class JobApisController {

    private final SampleJobsService jobsService;

    @PostMapping(path = "/groups/{group}/jobs")
    public ResponseEntity<JobDescriptor> createJob(@PathVariable String group, @RequestBody JobDescriptor descriptor) {
        return new ResponseEntity<>(jobsService.createJob(group, descriptor), CREATED);
    }

    @GetMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<JobDescriptor> findJob(@PathVariable String group, @PathVariable String name) {
        return jobsService.findJob(group, name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/groups/{group}/jobs/all")
    public ResponseEntity<Void> findBtAllJob(@PathVariable String group) {
        try {
            jobsService.findByAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> updateJob(@PathVariable String group, @PathVariable String name, @RequestBody JobDescriptor descriptor) {
        jobsService.updateJob(group, name, descriptor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> deleteJob(@PathVariable String group, @PathVariable String name) {
        jobsService.deleteJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/pause")
    public ResponseEntity<Void> pauseJob(@PathVariable String group, @PathVariable String name) {
        jobsService.pauseJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/resume")
    public ResponseEntity<Void> resumeJob(@PathVariable String group, @PathVariable String name) {
        jobsService.resumeJob(group, name);
        return ResponseEntity.noContent().build();
    }

}
