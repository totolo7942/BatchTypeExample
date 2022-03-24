package com.example.batchtypeexample.dynamic.apis;

import com.example.batchtypeexample.dto.JobDescriptor;
import com.example.batchtypeexample.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class ApisController {

    private final EmailService emailService;

    @PostMapping(path = "/groups/{group}/jobs")
    public ResponseEntity<JobDescriptor> createJob(@PathVariable String group, @RequestBody JobDescriptor descriptor) {
        return new ResponseEntity<>(emailService.createJob(group, descriptor), CREATED);
    }

    @GetMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<JobDescriptor> findJob(@PathVariable String group, @PathVariable String name) {
        return emailService.findJob(group, name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> updateJob(@PathVariable String group, @PathVariable String name, @RequestBody JobDescriptor descriptor) {
        emailService.updateJob(group, name, descriptor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> deleteJob(@PathVariable String group, @PathVariable String name) {
        emailService.deleteJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/pause")
    public ResponseEntity<Void> pauseJob(@PathVariable String group, @PathVariable String name) {
        emailService.pauseJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/resume")
    public ResponseEntity<Void> resumeJob(@PathVariable String group, @PathVariable String name) {
        emailService.resumeJob(group, name);
        return ResponseEntity.noContent().build();
    }

}
