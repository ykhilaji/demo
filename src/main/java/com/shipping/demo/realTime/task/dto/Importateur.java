package com.shipping.demo.realTime.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Importateur {
    private String company;
    private String reference;
    private String name;
    private String firstname;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String countryIso;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    private String cellular;
}
