package com.shipping.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MontantDesOptions {
    private List<TarifOption> tarifDesOptions;

    public Double findMontantOption(String codeOption) {
        if (tarifDesOptions != null) {
            for (TarifOption t : tarifDesOptions) {
                if (t.getOption() != null && codeOption.equals(t.getOption().getCodeOption())) {
                    return t.getMontantHT();
                }
            }
        }
        return null;
    }
}