package com.coliship.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfosOrigine {
    private String id;
    private String factureOrigine;
    private String factureDate;
    private String numColis;
}
