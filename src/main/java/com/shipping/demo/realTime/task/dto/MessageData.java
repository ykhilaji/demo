package com.shipping.demo.realTime.task.dto;

import java.time.Instant;

public record MessageData(String content, Instant timestamp) {}
