package com.shipping.demo.realTime.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TaskStatus {
    @JsonProperty("Submitted") SUBMITTED,
    @JsonProperty("Successful") SUCCESSFUL,
    @JsonProperty("BusinessError") BUSINESS_ERROR,
    @JsonProperty("TechnicalError") TECHNICAL_ERROR,
    @JsonProperty("Cancelled") CANCELLED,
    @JsonProperty("Warning") WARNING;
}
