package com.coliship.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointRetrait {
    private String identifiant;
    private String typeDePoint;
    private String company;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String countryIso;
    private String zipcode;
    private String city;
    private String accountNumber;
    @Builder.Default
    private Boolean isManual = false;
}

