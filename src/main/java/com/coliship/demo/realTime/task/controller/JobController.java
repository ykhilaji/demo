package com.coliship.demo.realTime.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coliship.demo.realTime.task.service.JobService;
import com.coliship.demo.realTime.task.utils.Mock;

@RestController
@RequestMapping("/job")
public class JobController {

   private final Mock mock;
   private final JobService jobService;

   public JobController(Mock mock, JobService jobService) {
    this.mock = mock;
    this.jobService = jobService;
}
    
}
