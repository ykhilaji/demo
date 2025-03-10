package com.shipping.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Groupage {
    private boolean isColisSuiveur;
    private Integer number;
    private Integer totalNumber;
}
