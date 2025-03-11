package com.shipping.demo.realTime.task.service;

import com.shipping.demo.realTime.task.entity.TaskInfra;
import com.shipping.demo.realTime.task.entity.TaskModel;

import reactor.core.publisher.Flux;

public interface JobService {
    public void onTask(TaskInfra task);
    public Flux<TaskModel> getTasks();
}
