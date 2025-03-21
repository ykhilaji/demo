package com.shipping.demo.realTime.task.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {
    private String code;
    private String label;
    @Builder.Default
    private Boolean flag = false;
}

