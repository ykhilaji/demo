package com.shipping.demo.realTime.task.service;

import com.shipping.demo.realTime.task.model.TaskInfra;

public interface JobService {
    public void onTask(TaskInfra task);
}
