package com.shipping.demo.realTime.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaracteristiquesModel {
    private String poidskg;
    private String refcommande;
    private String datedepot;
    private boolean genererRetour;
    private Boolean signature;
    private Boolean engagementDelai;
    @Builder.Default
    private Boolean economique = false;
    private boolean nonMachinable;
    private Options options;
    private boolean isCN23;
    private boolean genererCN23;
    private String hauteur;
    private String largeur;
    private String longueur;
    private String envoi;
    private String certificat;
    private String facture;
    private String licence;
    private String observations;
    private List<ArticleModel> articles;
    private InstructionNonLivraison retourOption;
    private String formatColis;
    private Double poidsVolumetrique;
    private List<InfosOrigine> infosOrigines;
    private Double contreRemboursement;
    private String enteteLigneColis;
    private String explications;
    private Importateur importateur;
    private String fraisDePort;
    private String invoiceNumber;
    private String referenceDouane;
    private String numEori;
    private String tagUsers;
    @Builder.Default
    private Boolean isDDP = false;
    private String descriptionGen;
    private Groupage groupage;
}

