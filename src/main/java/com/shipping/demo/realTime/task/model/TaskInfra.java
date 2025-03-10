package com.shipping.demo.realTime.task.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class TaskInfra {
    @JsonProperty("sid")
    private String sid;
    
    @JsonProperty("info")
    private String info;
    
    @JsonProperty("data")
    private Object data;
    
    @Nullable
    @JsonProperty("task")
    private TaskModel task;
}