package com.shipping.demo.realTime.task.service;

import com.shipping.demo.realTime.task.model.TaskInfra;
import com.shipping.demo.realTime.task.model.TaskModel;

import reactor.core.publisher.Flux;

public interface JobService {
    public void onTask(TaskInfra task);
    public Flux<TaskModel> getTasks();
}
