package com.shipping.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressModel {
    private String reference;
    private String company;
    private String service;
    private String name;
    private String firstname;
    private String phone;
    private String cellular;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String zipcode;
    private String city;
    private String countryIso;
    private String provinceOuEtatName;
    private String stateOrProvinceCode;
    private String doorcode1;
    private String doorcode2;
    private String intercom;
    @Builder.Default
    private Boolean isPro = false;
    private String promotionCode;
    private String infocomp;
    private String commercialName;
}
