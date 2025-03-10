package com.shipping.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Options {
    private Boolean avisReception;
    private String contreRemboursement;
    private String recommandation;
    private String valeurAssuree;
    private String langueNotification;
    private Boolean deliveryDutyPaid;
    private Boolean francTaxesDroits;
}