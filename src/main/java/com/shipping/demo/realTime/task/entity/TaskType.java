package com.shipping.demo.realTime.task.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import static java.util.Collections.unmodifiableSet;

public enum TaskType {
    @JsonProperty("GenerateLabel") GENERATE_LABEL,
    @JsonProperty("CheckGenerateLabel") CHECK_GENERATE_LABEL,
    @JsonProperty("MergePdf") MERGE_PDF,
    @JsonProperty("PrintAuto") PRINT_AUTO,
    @JsonProperty("PrintAutoCmd") PRINT_AUTO_CMD,
    @JsonProperty("PrintUnite") PRINT_UNITE,
    @JsonProperty("PrintHistory") PRINT_HISTORY,
    @JsonProperty("ImportFile") IMPORT_FILE,
    @JsonProperty("AutoCodeProduitCorrection") AUTO_CODE_PRODUIT_CORRECTION;

    public static Set<TaskType> desktopTasks() {
        return unmodifiableSet(Set.of(
            PRINT_AUTO, 
            PRINT_UNITE, 
            PRINT_HISTORY, 
            IMPORT_FILE
        ));
    }
}