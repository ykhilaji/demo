package com.shipping.demo.realTime.task.mapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InstructionNonLivraison {
    RIEN_FAIRE(3),
    RETOURNER(2);
    @JsonValue
    private final int value;

    InstructionNonLivraison(int value) {
      this.value = value;
    }
    
    @JsonCreator
    public static InstructionNonLivraison fromValue(Object value) {
        if (value instanceof Number) {
            int intValue = ((Number) value).intValue();
            for (InstructionNonLivraison instr : values()) {
                if (instr.value == intValue) {
                    return instr;
                }
            }
        } else if (value instanceof String) {
            String str = ((String) value).trim().toUpperCase();
            try {
                // On essaye de convertir directement le nom
                return InstructionNonLivraison.valueOf(str);
            } catch (IllegalArgumentException e) {
                // Si la chaîne représente un nombre, on la parse
                try {
                    int intValue = Integer.parseInt(str);
                    for (InstructionNonLivraison instr : values()) {
                        if (instr.value == intValue) {
                            return instr;
                        }
                    }
                } catch (NumberFormatException nfe) {
                    // Rien n'a fonctionné
                }
            }
        }
        throw new IllegalArgumentException("Valeur inconnue pour InstructionNonLivraison: " + value);
    }
}

