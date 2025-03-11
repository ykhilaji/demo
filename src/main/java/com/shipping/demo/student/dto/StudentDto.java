package com.shipping.demo.student.dto;

import lombok.Data;

// @Data
// public class StudentDto {
//   private String firstName;
//   private String lastName;
//   private int age;     
// }
public record StudentDto(
    String firstName,
    String lastName,
    int age
) {}
